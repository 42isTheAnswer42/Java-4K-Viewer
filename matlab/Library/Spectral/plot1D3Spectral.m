function plot1D3Spectral( theCurves, theWinTitle)
%Usage: plot1D3Spectral( theCurves, theWinTitle);
%Optional: theWinTitle

if exist( 'theWinTitle')==0
    plot1D3( theCurves, 380:10:730, [ 'r', 'g', 'b']);
else
    plot1D3( theCurves, 380:10:730, [ 'r', 'g', 'b'], theWinTitle);
end
