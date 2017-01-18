function [] = create_bluenoise_gaussian(InputSize, Dir, FilterVal )

% Create Noise
whitenoise = unifrnd(0, 1,InputSize.h,InputSize.w);
for i=1:FilterVal.amount
    %     Create Filtersettings
    filtersettings=fspecial('gaussian', [FilterVal.matrix_x FilterVal.matrix_y],FilterVal.sigma);
    %     Filter Whitenoise
    lowpass_whitenoise=imfilter(whitenoise,filtersettings,'replicat');
    %     Subtract Lowpass filtered whitenose from original whitenoise
    highpass_filtered=whitenoise-lowpass_whitenoise;
    
    %     Naming and Saving // add random number to make individual names
%     name = strcat('BlueNoise_Gaussian_',int2str(InputSize.w),'x',int2str(InputSize.h),'_',FilterVal,'_',int2str(unifrnd(0, 1000,1,1)),'_blx.tif');
    name = strcat('BlueNoise_Gaussian_',int2str(InputSize.w),'x',int2str(InputSize.h),'_Filterspecs_',num2str(FilterVal.matrix_x),'x', num2str(FilterVal.matrix_y),'_sigma',num2str(FilterVal.sigma),'_',int2str(i),'_blx.tif');
    cd(Dir.Result);
    imwrite(highpass_filtered,name);
end

end