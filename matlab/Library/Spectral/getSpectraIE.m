function theSpectra = getSpectraIE( theI1Filename)
%Usage: theSpectra = getSpectraIE( theI1Filename);
%Keywords for fheI1Filename: 
%		'ColorChecker'
%		'Krinov'
%		'Surfaces'
%		'VrhelDupontPaintChips'
%		'VrhelMunsellChips'
%		'VrhelNaturalObjects'
%		'All'
%		'Vrhel'

theSpectra = getSpectra( theI1Filename, 380:10:730);
