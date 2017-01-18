function [ OutCieDim, OutCieAve, OutCieDark,OutRGBWorkingSpace ] = create_ciecam02Images_v2( CroppedImage,clearImageFilename,profileChoice,surround )
%% CIECAM02
text = strcat({'Ciecam Bearbeitung für '}, clearImageFilename, {' hat begonnen.'});
disp(text);

OutputChoice = profileChoice.Output;
InputChoice = profileChoice.Input;

% Parameter für CIECAM02 setzen
La = 100;
La_out = 40;
surround_in=surround.input;

%Matrix for converting sRGB to XYZ and vice versa / W
%Quelle: http://www.brucelindbloom.com/index.html?Eqn_RGB_XYZ_Matrix.html
sRGB2XYZ= [ 0.4124564,  0.3575761,  0.1804375;...
    0.2126729,  0.7151522,  0.0721750;...
    0.0193339,  0.1191920,  0.9503041];
adobeRGB2XYZ = [ 0.5767309 , 0.1855540 , 0.1881852;...
    0.2973769 , 0.6273491 , 0.0752741;...
    0.0270343,  0.0706872 , 0.9911085];
% 
% sRGB2XYZ_D50= [0.43607474,  0.3850649,  0.1430804;...
%     0.2225045,  0.7168786,  0.0606169;...
%      0.0139322,  0.0971045,  0.7141733];
% 
% adobeRGB2XYZ_D50 = [ 0.6097559,  0.2052401 , 0.1492240;...
%      0.3111242 , 0.6256560 , 0.0632197;...
%      0.0194811 , 0.0608902 , 0.7448387];

%sRGB oder AdobeRGB
if(strcmp(InputChoice,'sRGB'))
    InputMatrix = sRGB2XYZ;
    InRGBWorkingSpace = 'sRGB';
end
if(strcmp(InputChoice,'adobeRGB'))
    InputMatrix = adobeRGB2XYZ;
    InRGBWorkingSpace = 'adobeRGB';
end

if(strcmp(OutputChoice,'sRGB'))
    OutputMatrix = sRGB2XYZ;
    OutRGBWorkingSpace = 'sRGB';
end
if(strcmp(OutputChoice,'adobeRGB'))
    OutputMatrix = adobeRGB2XYZ;
    OutRGBWorkingSpace = 'adobeRGB';
end



% make Image Double
myImage_double = im2double(CroppedImage);
% get WhitePoint
myImage_double(1,1,1:3) = 1;

% %Farbtemp D65
myIllumination = getT(5000);

% %Turn Image to Linear Gamma
% myImage_Lin= ((myImage_double)).^2.2;

% % 
% myXYZ_IN = imColorTransform(myImage_Lin, 'ICCProfiles/sRGBLinear.icc', 'ICCProfiles/D65_XYZ.icc'); 
myXYZ_IN = imColorTransform(myImage_double, 'ICCProfiles/sRGB.icm', 'ICCProfiles/D65_XYZ.icc'); 

% %XYZ White
myXYZ_W = getXYZWhite(myIllumination);

% %CIECAM02 Forwärtstransformation
myCAA = imXYZ2CAM02(myXYZ_IN, myXYZ_W, La*0.2, surround_in,  1, myXYZ_W( 2) * 0.2);
% %CIECAM02 Rückwärtstransformation
myXYZCheck_dim = imCAM02_2XYZ( myCAA, myXYZ_W, La_out*0.2, 'dim', 1, myXYZ_W( 2) * 0.2);
myXYZCheck_average = imCAM02_2XYZ( myCAA, myXYZ_W, La_out*0.2, 'average', 1, myXYZ_W( 2) * 0.2);
myXYZCheck_dark = imCAM02_2XYZ( myCAA, myXYZ_W, La_out*0.2, 'dark', 1, myXYZ_W( 2) * 0.2);

%Conversion to sRGB
% myNewLinCIECAM_dim = imColorTransform(myXYZCheck_dim, 'ICCProfiles/D65_XYZ.icc', 'ICCProfiles/sRGBLinear.icc'); 
% myNewLinCIECAM_average = imColorTransform(myXYZCheck_average, 'ICCProfiles/D65_XYZ.icc', 'ICCProfiles/sRGBLinear.icc');
% myNewLinCIECAM_dark = imColorTransform(myXYZCheck_dark, 'ICCProfiles/D65_XYZ.icc', 'ICCProfiles/sRGBLinear.icc');

myNewCIECAM_dim = imColorTransform(myXYZCheck_dim, 'ICCProfiles/D65_XYZ.icc', 'ICCProfiles/sRGB.icm'); 
myNewCIECAM_average = imColorTransform(myXYZCheck_average, 'ICCProfiles/D65_XYZ.icc', 'ICCProfiles/sRGB.icm');
myNewCIECAM_dark = imColorTransform(myXYZCheck_dark, 'ICCProfiles/D65_XYZ.icc', 'ICCProfiles/sRGB.icm');

%Turn Image back to 2.2 Gamma before saving
% myNewCIECAM_dim = ((myNewLinCIECAM_dim).^(1/2.2));
% myNewCIECAM_average = ((myNewLinCIECAM_average).^(1/2.2));
% myNewCIECAM_dark = ((myNewLinCIECAM_dark).^(1/2.2));

myNewCIECAM_dim_new = myNewCIECAM_dim * (1/max(myNewCIECAM_dim(1,1,1:3)));
myNewCIECAM_average_new = myNewCIECAM_average * (1/max(myNewCIECAM_average(1,1,1:3)));
myNewCIECAM_dark_new = myNewCIECAM_dark * (1/max(myNewCIECAM_dark(1,1,1:3)));

OutCieDim = im2uint16(myNewCIECAM_dim_new);
OutCieAve = im2uint16(myNewCIECAM_average_new);
OutCieDark = im2uint16(myNewCIECAM_dark_new);

%output status text
text = strcat({'Ciecam bearbeitung für '}, clearImageFilename, {' ist vorbei.'});
disp(text);
end

