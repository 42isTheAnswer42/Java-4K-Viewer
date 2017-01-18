function theCamRGB = getRealCamRGBfromTestchart( theFilename)
%Usage: theCamRGB = getRealCamRGBfromTestchart( theFilename);

%Simulation ausschalten:
myCamControl.fUseRGBfromMeasurement = 1;

myRGB_Raw = getCamRGBfromTestchart( theFilename, myCamControl);

%Weiﬂabgleich
myRGB_WB = doWB_RGB( myRGB_Raw, myRGB_Raw( 45, :));

%RGB der inneren Patches holen:
theCamRGB = getInternalPatches( myRGB_WB);

