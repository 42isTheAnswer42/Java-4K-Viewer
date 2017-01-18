function [  ] = create_noiseMovie( Dir, Files, clearImageFilename,videolength,InputNoiseAmount )
fps = 30;
filename = strcat(clearImageFilename,'noisemovie_',int2str(fps),'fps_',int2str(InputNoiseAmount),'Noise_',num2str(videolength),'s_vid.avi');
fullvideoname = fullfile(Dir.Result,filename);

% outputVideo = VideoWriter(fullvideoname,'Uncompressed AVInum
outputVideo = VideoWriter(fullvideoname);
disp('Start Video');
open(outputVideo)

% calculate videolentgh from amount of inputed noiesfiles
VideoLengthInFrames = round(videolength*(fps/length(Files)));

for j = 1:VideoLengthInFrames
    for i = 1:length(Files)
        writeVideo(outputVideo, Files{i})
    end
end
close(outputVideo)

disp('Video Finish');
% Finalize the video file.

end