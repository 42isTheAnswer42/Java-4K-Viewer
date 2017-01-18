function theTransformedImage = imColorTransformMac( theInImage, IProf, OProf)
%Usage: theTransformedImage = imColorTransformMac( theInImage, IProf, OProf);
%Hint: uses relative colorimetric rendering intent

switch IProf
    case '*sRGB'
        myIProfStruct = iccread( 'ICCProfiles\XYZ.ICM');
        myInImage = imRefTrafo( theInImage, 'srgb2xyz');
    case '*Lab'
        myIProfStruct = iccread( 'ICCProfiles\XYZ.ICM');
        myInImage = imRefTrafo( theInImage, 'lab2xyz');
    case '*XYZ'
        myIProfStruct = iccread( 'ICCProfiles\XYZ.ICM');
        myInImage = theInImage;
    otherwise
        if( strcmp( OProf, '*sRGB') || strcmp( OProf, '*XYZ') || strcmp( OProf, '*Lab') )
            myIProfStruct = iccread( IProf);
            %Trafo-Struktur erzeugen
            myTrafo = makecform( 'icc', myIProfStruct, iccread( 'ICCProfiles\XYZ.ICM'), ...
                                     'SourceRenderingIntent', 'RelativeColorimetric', 'DestRenderingIntent', 'RelativeColorimetric');
            %ICC Trafo anwenden:
            myInImage = applycform( theInImage, myTrafo);
        else 
            myIProfStruct = iccread( IProf);
            myInImage = theInImage;
        end
end

switch OProf
    case '*sRGB'
        myIProf = 'ICCProfiles\XYZ.ICM';
        myOutImage = imRefTrafo( myInImage, 'xyz2srgb');
    case '*Lab'
        myIProf = 'ICCProfiles\XYZ.ICM';
        myOutImage = imRefTrafo( myInImage, 'xyz2lab');
    case '*XYZ'
        myIProf = 'ICCProfiles\XYZ.ICM';
        myOutImage = myInImage;
    otherwise
        %Trafo-Struktur erzeugen
        myTrafo = makecform( 'icc', myIProfStruct, iccread( OProf), ...
                                 'SourceRenderingIntent', 'RelativeColorimetric', 'DestRenderingIntent', 'RelativeColorimetric');
        %ICC Trafo anwenden:
        myOutImage = applycform( myInImage, myTrafo);
end

theTransformedImage = myOutImage;
