function [] = create_noiseSamples( Dir,InputImage )

Cropstyle.cut = 1;
InputRatio.W = 16;
InputRatio.H = 9;
InputSize.W = 1920;
InputSize.H = 1080;

    filename = char(InputImage);
    InputUrl = strcat(Dir.Image,filename);
    ReadImage_raw = im2uint16(imread(InputUrl));

% Read NoiseImages
noiseimages  = dir([Dir.BlueNoise '*_blx.tif']);
for i = 1:length(noiseimages)
    NoiseFiles{i} = imread([Dir.BlueNoise noiseimages(i).name]);
end
% Create Filename
filename = char(InputImage);
if(numel(filename) == 1);
    filename = ImageFilesArray;
    clearImageFilename = filename(1:end-4);
else
    clearImageFilename = filename(1:end-4);
end

CroppedImage = create_croppedImage(clearImageFilename, ReadImage_raw, InputSize,InputRatio,Cropstyle);

%%NoiseBilder einlesen
ReadImage = CroppedImage;
ReadImageDouble = im2double(CroppedImage);

%Bildgröße auschecken
sizeOfImage= size(ReadImage);
maxImageSize = size(cell2mat(NoiseFiles(1)));

%%Noiselevel
% %incming value is between 0 and 10
NoiseLevelSpace =linspace(0,10,10);
% defines the max level of the Noiselevel
NoiseLevel = 0.1;
factor = (0.5/NoiseLevel);
cd(Dir.Result);
mkdir('Noise Samples');
cd('Noise Samples');
% Start
if(sizeOfImage(1) <= maxImageSize(1) & sizeOfImage(2) <= maxImageSize(2))
    
    for i = 1:numel(NoiseFiles)
        %       Read Noise File, make double and
        NoiseFile_i = cell2mat(NoiseFiles(i));
        %         make noise image double
        NoiseFile_double = im2double(NoiseFile_i);
        %     check size of image and noisefile
        sizeOfNoise= size(NoiseFile_double);
        if((sizeOfImage(1) < sizeOfNoise(1) & sizeOfImage(2) <  sizeOfNoise(2)))
            NoiseFile_double = imcrop(NoiseFile_double,[0 0 sizeOfImage(2) sizeOfImage(1)]);
        end
        
        %     Make NoiseFile 3D
        NoiseFile_3_pre = cat(3,NoiseFile_double,NoiseFile_double,NoiseFile_double);
        k=1;
        for j=min(NoiseLevelSpace):(NoiseLevelSpace(2)-NoiseLevelSpace(1)):max(NoiseLevelSpace)
            
            % j = InputNoiseAmount;
            %      Normalizes Noiselevel to NoiseLevel variable
            
            NoiseFile_x3_reduced = (NoiseFile_3_pre/factor)-(max(max(max(NoiseFile_3_pre/factor)))/2);
            %max(max(max(NoiseFile_x3_reduced)))
            %min(min(min(NoiseFile_x3_reduced)))
            
            %     Adjust values // /256 because of change to double
            if(j==0)
                %                 NoiseFile_set = NoiseFile_x3_reduced;
                NoiseAndImage = ReadImageDouble;
            else
                NoiseFile_set =   (NoiseFile_x3_reduced/10)*j;
                %   Add Noise to Image and Normalize to 1 as max value
                NoiseAndImage= plus(ReadImageDouble,NoiseFile_set);
            end
            
            
            Normalized_NoiseAndImage = NoiseAndImage * 1/(max(max(max(NoiseAndImage))));
            %   Eliminate all values below zero
            Normalized_NoiseAndImage_abs = max(0,Normalized_NoiseAndImage);
            
            ImagePlusNoiseDouble{i} = Normalized_NoiseAndImage_abs;
            texts = strcat('NoiseSample_',clearImageFilename,'_',num2str(k),'.tif');
            imwrite(Normalized_NoiseAndImage_abs,texts);
            %             text = strcat( num2str(j), ' REDUCER-Faktor ');
            %             figure('NAME',text);
            %             imshow( Normalized_NoiseAndImage_abs);
            k=k+1;
        end
    end
    %Make Gif File from Noise Files and Safe in OutputDir
    %     create_noiseMovie(Dir, ImagePlusNoiseDouble, clearImageFilename, length);
else
    disp('Image is too big for Noisefile, pick smaller Image or activate cropping');
end