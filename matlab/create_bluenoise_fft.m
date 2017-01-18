function create_bluenoise_fft(InputSize, Dir, FilterVal)
%Noise
N=1000;
for j = 1:FilterVal.amount
    tic
    % Create 1d bluenoise and make matrix with vertcat
    image= bluenoise_1d(N,FilterVal);
    for i =1:(N-1)
        noise= bluenoise_1d(N,FilterVal);
        image = vertcat(image,noise);
    end
    % Normalize
    image_n = image/max(max(image));
    % Replicate MxM by checking maximum Inputsize
    m_factor = round(max(InputSize.w ,InputSize.h)/N);
    noisepicture_big = repmat(image_n,m_factor);
    %   Cropping Noise Image to size
    noisepicture_crop = imcrop(noisepicture_big, [0 0 InputSize.w InputSize.h]);
    
    % Naming and Saving // add random number to make individual names
    %     name = strcat('BlueNoise_FFT_',int2str(InputSize.w),'x',int2str(InputSize.h),'_',num2str(FilterVal.cutoff),'Hz_',int2str(unifrnd(0, 1000,1,1)),'_blx.tif');
    name = strcat('BlueNoise_FFT_',int2str(InputSize.w),'x',int2str(InputSize.h),'_',num2str(FilterVal.cutoff),'Hz_',num2str(j),'_blx.tif');
    cd(Dir.Result);
    imwrite(noisepicture_crop,name);
    toc
end
cd(Dir.Main);
end
