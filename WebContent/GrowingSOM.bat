del output\*.*
call "..\..\Program Files (x86)\SOMToolbox\somtoolbox.bat" GrowingSOM MySOM_WIN.prop
del MySOM.dwm
call "C:\Program Files\7-Zip\7z.exe" x "output\MySOM.dwm.gz"