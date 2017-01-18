function theTransformedImage = imColorTransformWindows( theInImage, IProf, OProf)
%Usage: theTransformedImage = imColorTransformWindows( theInImage, IProf, OProf);
%Hint: uses relative colorimetric rendering intent

myOpt = ['-t1 -i ', IProf, ' -o ', OProf];
theTransformedImage = icctrans( theInImage, myOpt);
