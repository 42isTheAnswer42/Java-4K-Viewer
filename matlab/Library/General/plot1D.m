function plot1D( theCurve, theXGrid, theColor, theWinTitle)
%Usage: plot1D( theCurve, theXGrid, theColor, theWinTitle);
%Optional: theWinTitle

if exist( 'theWinTitle')==0 || isempty(theWinTitle)==1
    figure();
else
    figure( 'Name', theWinTitle, 'NumberTitle', 'off');
end
plot( theXGrid, theCurve( :), theColor( 1));
