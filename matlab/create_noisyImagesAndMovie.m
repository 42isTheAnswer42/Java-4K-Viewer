function [  ] = create_noisyImagesAndMovie( Dir,CroppedImage, clearImageFilename, InputNoiseFiles, InputNoiseAmount,videolength )
%%NoiseBilder einlesen
cd(Dir.Result);
ReadImage = CroppedImage;
ReadImageDouble = im2double(CroppedImage);

%Bildgröße auschecken
sizeOfImage= size(ReadImage);
maxImageSize = size(cell2mat(InputNoiseFiles(1)));


%%Noiselevel
% %incming value is between 0 and 10
NoiseLevelSpace =linspace(0,5,11);
% defines the max level of the Noiselevel
NoiseLevel = 0.1;

if(sizeOfImage(1) <= maxImageSize(1) & sizeOfImage(2) <= maxImageSize(2))
    
    for i = 1:numel(InputNoiseFiles)
        %       Read Noise File, make double and
        NoiseFile_i = cell2mat(InputNoiseFiles(i));
        %         make noise image double
        NoiseFile_double = im2double(NoiseFile_i);
        %     check size of image and noisefile
        sizeOfNoise= size(NoiseFile_double);
        if((sizeOfImage(1) < sizeOfNoise(1) & sizeOfImage(2) <  sizeOfNoise(2)))
            NoiseFile_double = imcrop(NoiseFile_double,[0 0 sizeOfImage(2) sizeOfImage(1)]);
        end
        
        %     Make NoiseFile 3D
        NoiseFile_3_pre = cat(3,NoiseFile_double,NoiseFile_double,NoiseFile_double);
        
        j = NoiseLevelSpace(InputNoiseAmount+1);
        %      Normalizes Noiselevel to NoiseLevel variable
        factor = (0.5/NoiseLevel);
        NoiseFile_x3_reduced = (NoiseFile_3_pre/factor)-(max(max(max(NoiseFile_3_pre/factor)))/2);
        %max(max(max(NoiseFile_x3_reduced)))
        %min(min(min(NoiseFile_x3_reduced)))
        
        %     Adjust values // /256 because of change to double
        if(j==0)
            %                 NoiseFile_set = NoiseFile_x3_reduced;
            NoiseAndImage = ReadImageDouble;
        else
            NoiseFile_set = (NoiseFile_x3_reduced/10)*j;
            %   Add Noise to Image and Normalize to 1 as max value
            NoiseAndImage= plus(ReadImageDouble,NoiseFile_set);
        end
        
        
        Normalized_NoiseAndImage = NoiseAndImage * 1/(max(max(max(NoiseAndImage))));
        %   Eliminate all values below zero
        Normalized_NoiseAndImage_abs = max(0,Normalized_NoiseAndImage);
        
        ImagePlusNoiseDouble{i} = Normalized_NoiseAndImage_abs;
        
    end
    %Make Gif File from Noise Files and Safe in OutputDir
    create_noiseMovie(Dir, ImagePlusNoiseDouble, clearImageFilename, videolength,InputNoiseAmount);
else
    disp('Image is too big for Noisefile, pick smaller Image or activate cropping');
end