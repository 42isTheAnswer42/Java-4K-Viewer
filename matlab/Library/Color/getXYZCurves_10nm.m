function theXYZCurves_10nm = getXYZCurves_10nm()
%Usage: theXYZCurves_10nm = getXYZCurves_10nm();

XYZCurves_5nm = load( 'XYZ2_5nm_380_735.mat');
theXYZCurves_10nm = XYZCurves_5nm.XYZ2_5nm_380_735( 1:2:end, :);


