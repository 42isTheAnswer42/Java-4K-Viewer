function [ output ] = main_processing(Dir,ImageFilesArray,ImageProfile,InputSize,surroundCiecam,NoiseLevel,Cropstyle,NoiseVideoDuration, Run )

MainDir = Dir.Main;
InputDir = Dir.Input;
ResultDir = Dir.Result;
ImageDir = Dir.Image;
BlueNoiseDir = Dir.BlueNoise;

InputRatio.W = 16;
InputRatio.H = 9;



if(Run.Noise == 1)
    % Read NoiseImages
    noiseimages  = dir([BlueNoiseDir '*_blx.tif']);
    for i = 1:length(noiseimages)
        NoiseFiles{i} = imread([BlueNoiseDir noiseimages(i).name]);
    end
end



%Create Counter for Iteration
filename = char(ImageFilesArray(1));
if(numel(filename) == 1);
    counterEnd=1;
else
    counterEnd = numel(ImageFilesArray);
end
tic
for i = 1:counterEnd
    filename = char(ImageFilesArray(i));
    InputUrl = strcat(ImageDir,filename);
    ReadImage = im2uint16(imread(InputUrl));
    
    %Dateinamen vorbereiten für Speichern
    %If Abfrage, zur vermeidung von Problemen wenn nur ein Bild ausgewählt
    %wird
    filename = char(ImageFilesArray(i));
    if(numel(filename) == 1);
        filename = ImageFilesArray;
        clearImageFilename = filename(1:end-4);
    else
        clearImageFilename = filename(1:end-4);
    end
    
    
    if(Run.Crop == 1)
        filenameAndSize =  strcat(clearImageFilename,'_',num2str(InputSize.W),'x',num2str(InputSize.H));
        cd(MainDir);
        %Check Ratio and Resolution
        myCroppedImage = create_croppedImage(clearImageFilename, ReadImage, InputSize,InputRatio,Cropstyle);
        cd(ResultDir);
        disp({'Crop Bild wird erzeugt'});
        if(Cropstyle.cut==1)
            imwrite(myCroppedImage, strcat(filenameAndSize,'_cutcropped.tif'));
        else
            imwrite(myCroppedImage, strcat(filenameAndSize,'_fillcropped.tif'));
        end
    else
        filenameAndSize =  strcat(clearImageFilename,'_',num2str(size(ReadImage,1)),'x',num2str(size(ReadImage,2)));
        disp({'Original Bild (uncropped) wird erzeugt'});
        imwrite(ReadImage, strcat(filenameAndSize,'_cropped.tif'));
    end
    
    if(Run.Ciecam == 1)
        cd(MainDir);
        % Ciecam
        if(Run.Crop ==1)
            [myCIECAM_dim,myCIECAM_average,myCIECAM_dark, RGBWorkingSpace] = create_ciecam02Images_v2(myCroppedImage, clearImageFilename,ImageProfile,surroundCiecam);
            cd(ResultDir);
            disp({'Ciecam Bilder werden erzeugt'});
            imwrite(myCIECAM_dim, strcat(filenameAndSize,'_',RGBWorkingSpace,'_CIECAM_dim.tif'));
            imwrite(myCIECAM_average, strcat(filenameAndSize,'_',RGBWorkingSpace,'_original.tif'));
            imwrite(myCIECAM_dark, strcat(filenameAndSize,'_',RGBWorkingSpace,'_CIECAM_dark.tif'));
        else
            [myCIECAM_dim,myCIECAM_average,myCIECAM_dark, RGBWorkingSpace] = create_ciecam02Images_v2(ReadImage, clearImageFilename,ImageProfile,surroundCiecam);
            cd(ResultDir);
            disp({'Ciecam Bilder werden erzeugt'});
            imwrite(myCIECAM_dim, strcat(clearImageFilename,'_',RGBWorkingSpace,'_CIECAM_dim.tif'));
            imwrite(myCIECAM_average, strcat(clearImageFilename,'_',RGBWorkingSpace,'_original.tif'));
            imwrite(myCIECAM_dark, strcat(clearImageFilename,'_',RGBWorkingSpace,'_CIECAM_dark.tif'));
        end
        
    end
    
    if(Run.Noise == 1)
        % Blue Noise Animation
        disp({'Noise Animation wird berechnet'});
        if(Run.Crop ==1)
            create_noisyImagesAndMovie( Dir, myCroppedImage ,filenameAndSize, NoiseFiles, NoiseLevel, NoiseVideoDuration );
        else
            create_noisyImagesAndMovie( Dir, ReadImage ,filenameAndSize, NoiseFiles, NoiseLevel, NoiseVideoDuration );
        end
    end
    %Bilder speichern
    
    
    
    zeit(i)= toc;
    text = strcat({'Das Bild '}, clearImageFilename, {' wurde in '}, int2str(zeit(i)), 's bearbeitet');
    disp(text);
    tic
end
output = 1;
text=strcat({'Verarbeitung abgeschlossen. Es wurden '}, int2str(counterEnd),{' Bilder in '}, int2str(sum(zeit)), 's verarbeitet');
disp(text);

end

