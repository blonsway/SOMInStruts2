<html>
<head>



<link rel="stylesheet" type="text/css" href="http://localhost:8080/generated/viz.css">
<script src="http://localhost:8080/generated/d3.min.js" charset="utf-8"></script>
<script src="http://localhost:8080/generated/visualJsonData.js"
	charset="utf-8"></script>
<script src="http://localhost:8080/generated/visualJsonLines.js"
	charset="utf-8"></script>
</head>

<body>


	<div id="primary">
		<div id="visualize"></div>
	</div>
	<div id="secondary"></div>


	<script>


		var textViz = "hidden",		// start visibility state for svg text blocks
			textBoxPadding = 10,   	// padding for svg text blocks
			techSectorDotColor = "#E68A00",
			socialSectorDotColor = "#CCFF66",
			blurbs = [], 			// array containing text blurbs from user selections
			itemNumber = 0,
			blurbNumber = 0,
			vizWidth = 1000,
			textBoxWidth = 300,
			boxX = 0;

		var blurbTestArray = [];

		//create svg
		var svgContainer = d3.select("#visualize")
			.append("svg")
	        .attr("width", vizWidth+10)
	        .attr("height", 2000)
	 	 	.append("g")
		    //.call(d3.behavior.zoom().scaleExtent([.5, 8]).on("zoom", zoom))
		  	.append("g");

  		//to catch drag operations
		var drag = d3.behavior.drag()
		    .on("dragstart", dragstarted)
		    .on("drag", dragged)
		    .on("dragend", dragended);

    	//overlay to catch all mouse events that occur outside of other drawn elements.
		svgContainer.append("rect")
		    .attr("class", "overlay")
		    .attr("width", vizWidth)
		    .attr("height", 1000);


 		//draw grid lines
		svgContainer.selectAll("line")
			.data(jsonLines)
			.enter()
			.append("line")
			.attr("x1", function (d) { return d.x1; })
			.attr("x2", function (d) { return d.x2; })
			.attr("y1", function (d) { return d.y1; })
			.attr("y2", function(d)  { return d.y2; })
			.attr("stroke-width", 1)
			.attr("stroke", "gainsboro");



			
		//Enter
		svgContainer.selectAll("circle")
			.data(jsonCircles)
			.enter()
			.append("circle")
				.attr("cx", function (d) { return d.x_axis; })
				.attr("cy", function (d) { return d.y_axis; })
				.attr("r", 10)
				.attr("class", "circle")
			    .attr("id", function(d) {return("dot" + d.documentNumber);})
				.attr("isSelected", 0)
				.attr("fill", function(d){ if (d.tech_sector == "1") {return techSectorDotColor;} else {return socialSectorDotColor;}});
		 

		 svgContainer.selectAll("text")
		 	.data(jsonCircles)
		 	.enter()
		 	.append("text")
              	.attr("x", function(d) { if (d.x_axis >= vizWidth/2) {return ((d.x_axis)-(textBoxWidth+20));} else {return d.x_axis+20;}; })
                .attr("y", function(d) { return d.y_axis+20; })
  				.attr("id", function(d) {return("textBlock" + d.documentNumber);})
  				.attr("visibility", "hidden")
          		.text(function(d) { return d.situation_description;})
          		.call(wrap, textBoxWidth, function(d) { if (d.x_axis >= vizWidth/2) {return ((d.x_axis)-(textBoxWidth+20));} else {return d.x_axis+20;}; }, function(d) { return (d.y_axis+20); });


		 svgContainer.selectAll("rect")
		 	.data(jsonCircles)
		 	.enter()
			.insert("rect", "text")
				.attr("class", "textPopUp")
			    .attr("x", function(d){ return (d3.select("#textBlock" + d.documentNumber).node().getBBox().x - textBoxPadding);})
			    .attr("y", function(d){ return (d3.select("#textBlock" + d.documentNumber).node().getBBox().y - textBoxPadding);})
			    .attr("width", function(d){ return (d3.select("#textBlock" + d.documentNumber).node().getBBox().width + (textBoxPadding*2));})
			    .attr("height", function(d){ return (d3.select("#textBlock" + d.documentNumber).node().getBBox().height + (textBoxPadding*2));})
			    .attr("id", function(d) {return("textBlockBG" + d.documentNumber);})
			    .attr("rx", textBoxPadding)
			    .attr("ry", textBoxPadding)
			    .attr("visibility","hidden");




		//Update
		svgContainer.selectAll("circle")
			.data(jsonCircles)
			.on("mouseover", function(d){
				//return console.log("#textBlock"+d.documentNumber);
				d3.select("#textBlock" + d.documentNumber).attr("visibility", "visible");
				d3.select("#textBlockBG"+ d.documentNumber).attr("visibility", "visible");
				})
			.on("mouseout", function(d){
				if (d.isSelected == 1) { 
					d3.select("#textBlock" + d.documentNumber).attr("visibility", "visible");
					d3.select("#textBlockBG"+ d.documentNumber).attr("visibility", "visible");
				} 
				else { 
					d3.select("#textBlock" + d.documentNumber).attr("visibility", "hidden");
					d3.select("#textBlockBG"+ d.documentNumber).attr("visibility", "hidden");}
				})
			.on("click", function(d){
				//d.isSelected = (d.isSelected == 1) ? 0 : 1;
				//blurbTestArray.push({itemNum: itemNumber++, docNum: d.documentNumber,});
				addToList(d.tech_sector,d.documentNumber,d.situation_description);})
			.call(drag);




		//Exit
		svgContainer.selectAll("circle")
			.data(jsonCircles)
			.exit()
				.remove();


		



		function wrap(text, wrapWidth, xLoc, yLoc) {
		  text.each(function() {
		    var text = d3.select(this),
		        words = text.text().split(/\s+/).reverse(),
		        word,
		        line = [],
		        lineNumber = 0,
		        lineHeight = 1.0, // ems
		        offset = 10,
		        y = text.attr("y"),
		        //dy = parseFloat(text.attr("dy")),
		        dy = 0,
		        tspan = text.text(null).append("tspan").attr("x", xLoc).attr("y", y).attr("dy", dy + "em");
		    while (word = words.pop()) {
		      line.push(word);
		      tspan.text(line.join(" "));
		      if (tspan.node().getComputedTextLength() > wrapWidth) {
		        line.pop();
		        tspan.text(line.join(" "));
		        line = [word];
			        tspan = text.append("tspan").attr("x", xLoc).attr("y", y).attr("dy", ++lineNumber * lineHeight + dy + "em").text(word);
		      }
		    }
		  });
		}



		function zoom() {
			svgContainer.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
		}

		function dragstarted(d) {
			  d3.event.sourceEvent.stopPropagation();
			  d3.select(this).classed("dragging", true);
		}

		function dragged(d) {
			  d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
		}

		function dragended(d) {
			d3.select(this).classed("dragging", false);
		}	  







function renderBlurbs(sector) {

	d3.select("#secondary").selectAll("div")
	.data(blurbs)
	.enter()
	.append("div")
	.text(function(d) { return (d);})
	.attr("class", function(){if (sector == "1") {return "inner-tech";} else {return "inner-social";};})
	.attr("id", "blurb"+(blurbNumber++));

	console.log ("creating blurb number:"+blurbNumber);

}

function addToList(sector,source,blurb) {

	blurbs.push(blurb);
	blurbTestArray.push({itemNum: itemNumber++, docNum: source });

	renderBlurbs(sector);

	console.log("source:"+source);
	console.log("dest: #blurb"+itemNumber);
	
	var element = document.getElementById('blurb'+(itemNumber-1));
	console.log(element);

	// var position = element.getBoundingClientRect();
	// var posX = position.left;
	// var posY = position.top;


	function position(el){ 
	    var left = 0, 
	        top = 0; 

	    do { 
	        left += el.offsetLeft-el.scrollLeft; 
	        top += el.offsetTop-el.scrollTop; 
	    } while ( el = el.offsetParent ); 

	    console.log([left, top]);
	    return [ left, top ]; 
	} 


	var scrollPos = position(element);
	var posX = scrollPos[0]+window.pageXOffset;
	var posY = scrollPos[1]+window.pageYOffset;


	//draw line to blurb
	// svgContainer.append("line")
	// 	.attr("x1", d3.select("#dot"+source).attr("cx"))
	// 	.attr("y1", d3.select("#dot"+source).attr("cy"))
	// 	.attr("x2", posX)
	// 	.attr("y2", posY)
 //     	.attr("stroke-width", 2)
	// 	.attr("stroke", "black");

	var lineFunction = d3.svg.line()
		.x(function(d) { return d.x; })
		.y(function(d) { return d.y; })
		.interpolate("basis");


		var xStart = d3.select("#dot"+source).attr("cx"),
			yStart = d3.select("#dot"+source).attr("cy");

		var lineCoords = 	[	
							{x: xStart, y: yStart},
							{x: xStart+50, y: yStart},
							{x: posX-50, y: posY},
							{x:posX, y:posY}
							]

	svgContainer.append("path")
        .attr("d", lineFunction(lineCoords))
        .attr("stroke", "black")
        .attr("stroke-width", 2)
        //.attr("stroke-dasharray","10,10")
        .attr("fill", "none");




}



		</script>


</body>
</html>