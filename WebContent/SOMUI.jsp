<html>
	<head>


		<style>


		body {

			background-color:#ccc;

		}

		.overlay {
			fill: #666;
			pointer-events: all;
		}

		.tooltip {

			position: absolute;
			width: 500px;
			text-align: justify;
			font-family: Sans-serif;
			font-size: small;
			line-height: 1.1em;
			background-color: #eee;
			padding: 5px;
			z-index: 10;
			visibility: hidden;

		}


		.circle {

		    fill: #aad;
		    stroke: black;

		}

		.circle.dragging {

		    fill: #adb;
		    stroke: black;

		}


		</style>

			<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
			<script src="/var/lib/tomcat7/webapps/ROOT/generated/visualJsonData.js" charset="utf-8"></script>
			<script src="/var/lib/tomcat7/webapps/ROOT/generated/visualJsonLines.js" charset="utf-8"></script>		
	</head>

	<body>

		<div id = "visualize"></div>


		<script>


			var svgContainer = d3.select("#visualize").append("svg")
			                                    .attr("width", 1000)
			                                    .attr("height", 1000)
			                             	 	.append("g")
											    .call(d3.behavior.zoom().scaleExtent([1, 8]).on("zoom", zoom))
											  	.append("g");

			svgContainer.append("rect")
			    .attr("class", "overlay")
			    .attr("width", 1000)
			    .attr("height", 1000);


			var drag = d3.behavior.drag()
			    .on("dragstart", dragstarted)
			    .on("drag", dragged)
			    .on("dragend", dragended);

			var circles = svgContainer.selectAll("circle")
			                          .data(jsonCircles)
			                          .enter()
			                          .append("circle");
									  
			var tooltip = d3.select("#visualize")
				.append("div")
				.data(jsonCircles)
				.attr("class", "tooltip");
									  
			var circleAttributes = circles
			    .attr("cx", function (d) { return d.x_axis; })
				.attr("cy", function (d) { return d.y_axis; })
				.attr("r", 10)
				.attr("class", "circle")
				.on("mouseover", function(d){ tooltip.text(d.situation_description); return tooltip.style("visibility", "visible");})
				.on("mousemove", function(d){tooltip.text(d.situation_description); return tooltip.style("top", (event.pageY-10)+"px").style("left",(event.pageX+10)+"px");})
				.on("mouseout", function(d){tooltip.text(d.situation_description); return tooltip.style("visibility", "hidden");})
              	.call(drag);

			var lines = svgContainer.selectAll("line")
				.data(jsonLines)
				.enter()
				.append("line");
									  
			var lineAttributes = lines
				.attr("x1", function (d) { return d.x1; })
				.attr("x2", function (d) { return d.x2; })
				.attr("y1", function (d) { return d.y1; })
				.attr("y2", function(d)  { return d.y2; })
				.attr("stroke-width", 1)
				.attr("stroke", "gainsboro");
								 

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
			}	  


								  
		</script>
	</body>
</html>