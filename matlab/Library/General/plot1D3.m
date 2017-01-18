function plot1D3( theCurves, theXGrid, theColor, theWinTitle)
%Usage: plot1D3( theCurves, theXGrid, theColor, theWinTitle);
%Optional: theWinTitle

if exist( 'theWinTitle')==0 || isempty(theWinTitle)==1
    figure();
else
    figure( 'Name', theWinTitle, 'NumberTitle', 'off');
end
hold on;
plot( theXGrid, theCurves( :, 1), theColor( 1));
plot( theXGrid, theCurves( :, 2), theColor( 2));
plot( theXGrid, theCurves( :, 3), theColor( 3));
hold off;
