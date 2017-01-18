function theTransformedImage = imColorTransform( theInImage, IProf, OProf)
%Usage: theTransformedImage = imColorTransform( theInImage, IProf, OProf);
%Hint: uses relative colorimetric rendering intent

% nur eine von beiden:
%theTransformedImage = imColorTransformWindows( theInImage, IProf, OProf);
theTransformedImage = imColorTransformMac( theInImage, IProf, OProf);
