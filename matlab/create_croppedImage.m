function [ OutputCropImage] = create_croppedImage(clearFileName, ReadImage, InputRes, InputRatio, Cropstyle )
% Read Size of Image
sizeOfImage= size(ReadImage);

%Output Resolution
outputSize = [InputRes.H InputRes.W];

clearImageFileName = clearFileName;
if (sizeOfImage(2) >= InputRes.W && sizeOfImage(1) >= InputRes.H)
    OutputCropImage = ReadImage;
    if(Cropstyle.cut == 1)
        
        
        %%%%%%%% Check Ratio
        if (sqrt(((sizeOfImage(2)/sizeOfImage(1))-(InputRatio.W/InputRatio.H))^2)< 0.010)
            text = strcat(clearImageFileName, {' hat das richtige Seitenverhältnis '});
            disp(text);
            
            OutputCropImage = imresize(ReadImage,outputSize);
            text = strcat({'Finish - das Bild '}, clearImageFileName, {' hat nun die Auflösung '},num2str(outputSize(2)),{' x '},num2str(outputSize(1)),  ' und wurde gecropped');
            disp(text);
        else
            text = strcat(clearImageFileName, {' wird nun gecropped'});
            disp(text);
            %%%%%%%%%%%%%%%%%%%%%% Crop
            % Bildgröße rausfinden
            % Maximela 16:9 Crop rausfinden
            % Croppen
            CHECK_ratio = [(sizeOfImage(1) / InputRatio.H) (sizeOfImage(2) / InputRatio.W)];
            RoundRatioVar = round(min(CHECK_ratio));
            
            %Wenn das Bild Hochkannt ist, frag nach welcher Teil gecropped
            %werden soll, Ansonsten wird immer der Mittlere Teil des Bildes
            %gecropped
            if(sizeOfImage(1)>sizeOfImage(2))
                QuestionText = strcat({'Select Crop Area for: '}, clearImageFileName);
                choice = questdlg(QuestionText, ...
                    'Select Crop Area', ...
                    'Top','Middle','Bottom','Middle');
                
                %%%MAXIMUM CROP
                %             rect = [xmin ymin width height]
                switch  choice
                    case 'Middle'
                        disp('middle')
                        rect = [0 (InHeigth/2 - (InputRatio.H*RoundRatioVar)/2 ) InputRatio.W*RoundRatioVar InputRatio.H*RoundRatioVar];
                    case 'Top'
                        disp('top')
                        rect = [0 0 InputRatio.W*RoundRatioVar InputRatio.H*RoundRatioVar];
                    case 'Bottom'
                        rect = [0 (InHeigth - (InputRatio.H*RoundRatioVar)) InputRatio.W*RoundRatioVar InputRatio.H*RoundRatioVar];
                end
                
                
            else
                rect = [0 (sizeOfImage(1)/2 - (InputRatio.H*RoundRatioVar)/2 ) InputRatio.W*RoundRatioVar InputRatio.H*RoundRatioVar];
            end
            clear choice;
            
            CropImage = imcrop(ReadImage,rect);
            OutputCropImage = imresize(CropImage,outputSize);
            text = strcat({'Finish - das Bild '}, clearImageFileName, {' hat nun die Auflösung '},num2str(outputSize(2)),'x',num2str(outputSize(1)),  ' und wurde gecropped');
            disp(text);
        end
        
        
    else
        % %    If Cropstyle.fill == 1
    ReadImage_double = im2double(ReadImage);
    
    
    % %    If picture is in landscape mode
    if(sizeOfImage(1) < sizeOfImage(2))
        croppedReadImage = imresize(ReadImage_double,outputSize(1)/size(ReadImage_double,1));
        blackstripe = zeros(size(croppedReadImage,1),round(abs((outputSize(2) - size(croppedReadImage,2))/2)));
        blackstripe_3 = cat(3,blackstripe,blackstripe,blackstripe);
        FilledImage = horzcat(croppedReadImage,blackstripe_3);
        OutputCropImage = horzcat(blackstripe_3,FilledImage);
        %         figure; imshow(FilledImage);
    else
        %         % %    If picture is in portrait mode
        %         ReadImage = permute(ReadImage,[2 1 3]);
        %         croppedReadImage = imresize(ReadImage_double,outputSize(1)/size(ReadImage_double,1));
        %         blackstripe = zeros(size(croppedReadImage,1),(outputSize(2) - size(croppedReadImage,2))/2);
        %         blackstripe_3 = cat(3,blackstripe,blackstripe,blackstripe);
        %         FilledImage = horzcat(croppedReadImage,blackstripe_3);
        %         FilledImage = horzcat(blackstripe_3,FilledImage);
        %         figure; imshow(FilledImage);
        %         FilledImage = permute(FilledImage,[2 1 3]);
        disp('Image is not in Landscape mode, cannot be cropped and filled');
    end
        
    end
    
else
    text = strcat({'NO - das Bild  '}, clearImageFileName, ' ist leider nicht groß genug');
    disp(text);
end
clear info;
clear choice;

end

