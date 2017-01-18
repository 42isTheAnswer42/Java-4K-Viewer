clear all;
clear hidden;
close all;

% seeks its own path and sets it
MainDirFull = which('mainv2');
MainDir = MainDirFull(1:end-8);

% set the path globally
assignin ('base','MainDir',MainDir);
addpath(genpath(evalin('base','MainDir')));
cd(evalin('base','MainDir'));

% start gui
gui;
    