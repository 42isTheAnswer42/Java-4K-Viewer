function [ output_args ] = create_checkerboard( pxi,wi,hi, Dir )
px = pxi;
h = hi;
w = wi;
checkerboard_name = strcat('checkerboard_',num2str(px),'px_',num2str(w),'x',num2str(h),'.tif');
checkerboard_img = checkerboard(px,h/(2*px),w/(2*px));
cd(Dir.Result);
imwrite(checkerboard_img,checkerboard_name);
cd(Dir.Main);
end

