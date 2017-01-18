function varargout = gui(varargin)


% GUI MATLAB code for gui.fig
%      GUI, by itself, creates a new GUI or raises the existing
%      singleton*.
%
%      H = GUI returns the handle to a new GUI or the handle to
%      the existing singleton*.
%
%      GUI('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in GUI.M with the given input arguments.
%
%      GUI('Property','Value',...) creates a new GUI or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before gui_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to gui_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help gui

% Last Modified by GUIDE v2.5 22-Nov-2016 19:15:52
% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
    'gui_Singleton',  gui_Singleton, ...
    'gui_OpeningFcn', @gui_OpeningFcn, ...
    'gui_OutputFcn',  @gui_OutputFcn, ...
    'gui_LayoutFcn',  [] , ...
    'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before gui is made visible.
function gui_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to gui (see VARARGIN)

% Choose default command line output for testui
handles.output = hObject;
% guidata(hObject, handles);

% % % SEt Defaults on Startup
% set(handles.Output_Heigth,'String',2160);
% set(handles.Output_Width,'String',3840);

% Set Radiobuttons
set(handles.Input_Profile_sRGB,'Value',1);
set(handles.Output_Profile_sRGB,'Value',1);
set(handles.Ciecam_In_Ave,'Value',1);

set(handles.cropcheckbox,'Value',1);
set(handles.ciecamcheckbox,'Value',1);
set(handles.noisecheckbox,'Value',1);

set(handles.cropandcut_radio,'Value',1);
set(handles.checkall_checkbox,'Value',1);

set(handles.noiselevel_edit,'String',num2str(5));
set(handles.videolength_edit,'String',num2str(4));
set(handles.bluenoise_amount,'String',num2str(10));


% % %Initialisien der Files wie in der ursprünglichen Main
handles.Dir.Main = evalin('base','MainDir');
cd(handles.Dir.Main);
% create Directorys in case there are not there
mkdir('NoiseImages');
mkdir('Results');
% check if system is a mac or windows and set slash accordingly
if ismac()
    slash = '/';
else
    slash = '\';
end
handles.Dir.Input = [ handles.Dir.Main, 'Input_JPG',slash];
handles.Dir.Result = [ handles.Dir.Main, 'Results',slash];
handles.Dir.BlueNoise = [ handles.Dir.Main, 'NoiseImages',slash];

% Set Noisedirectory default to MainDirectory
set(handles.noisedir_txt,'String',handles.Dir.BlueNoise);


% Set Savedirectory default to MainDirectory
set(handles.savedir_edit,'String',handles.Dir.Result);
addpath(genpath(handles.Dir.Main));
% Update handles structure
guidata(hObject, handles);

% --- Outputs from this function are returned to the command line.
function varargout = gui_OutputFcn(hObject, eventdata, handles)
% varargout  cell array for returning output args (see VARARGOUT);
% Get default command line output from handles structure
% varargout{1} = handles.output;



% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)



% --- Executes on selection change in listbox1.
function listbox1_Callback(hObject, eventdata, handles)
% Hints: contents = cellstr(get(hObject,'String')) returns listbox1 contents as cell array
%        contents{get(hObject,'Value')} returns selected item from listbox1


% --- Executes during object creation, after setting all properties.
function listbox1_CreateFcn(hObject, eventdata, handles)
% Hint: listbox controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end





% --- Executes during object creation, after setting all properties.
function ImageListbox_CreateFcn(hObject, eventdata, handles)
% Hint: listbox controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end






function width_Callback(hObject, eventdata, handles)
% Hints: get(hObject,'String') returns contents of width as text
%        str2double(get(hObject,'String')) returns contents of width as a double


% --- Executes during object creation, after setting all properties.
function width_CreateFcn(hObject, eventdata, handles)

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function heigth_Callback(hObject, eventdata, handles)
% Hints: get(hObject,'String') returns contents of heigth as text
%        str2double(get(hObject,'String')) returns contents of heigth as a double


% --- Executes during object creation, after setting all properties.
function heigth_CreateFcn(hObject, eventdata, handles)
% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end





% --- Executes during object creation, after setting all properties.
function Output_Width_CreateFcn(hObject, eventdata, handles)
% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function Output_Heigth_Callback(hObject, eventdata, handles)
% Hints: get(hObject,'String') returns contents of Output_Heigth as text
%        str2double(get(hObject,'String')) returns contents of Output_Heigth as a double



% --- Executes during object creation, after setting all properties.
function Output_Heigth_CreateFcn(hObject, eventdata, handles)
% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



% --- Executes during object creation, after setting all properties.
function stepbysteppanel_CreateFcn(hObject, eventdata, handles)

% --- Executes on button press in cropcheckbox.
function cropcheckbox_Callback(hObject, eventdata, handles)
% Hint: get(hObject,'Value') returns toggle state of cropcheckbox
if(get(handles.cropcheckbox,'Value')==0)
    set(handles.checkall_checkbox,'Value',0);
end


% --- Executes on button press in ciecamcheckbox.
function ciecamcheckbox_Callback(hObject, eventdata, handles)
% Hint: get(hObject,'Value') returns toggle state of ciecamcheckbox
if(get(handles.ciecamcheckbox,'Value')==0)
    set(handles.checkall_checkbox,'Value',0);
end

% --- Executes on button press in noisecheckbox.
function noisecheckbox_Callback(hObject, eventdata, handles)
if(get(handles.noisecheckbox,'Value')==0)
    set(handles.checkall_checkbox,'Value',0);
end

% Hint: get(hObject,'Value') returns toggle state of noisecheckbox
function Output_Width_Callback(hObject, eventdata, handles)

% Hints: get(hObject,'String') returns contents of Output_Width as text
%        str2double(get(hObject,'String')) returns contents of Output_Width as a double
width = str2double(get(handles.Output_Width,'String'));
heigth_calc = width*(9/16);
set(handles.Output_Heigth,'String',num2str(round(heigth_calc)));
% Set checkerboard and blue noise according to output size
set(handles.checkerboard_h_text,'String',num2str(round(heigth_calc)));
set(handles.noise_h,'String',num2str(round(heigth_calc)));

set(handles.checkerboard_w_text,'String',width);
set(handles.noise_w,'String',width);
guidata(hObject, handles);

% --- Executes on button press in Choose_Dir.
function Choose_Dir_Callback(hObject, eventdata, handles)
[ handles.ImageFiles, handles.Dir.Image] = uigetfile( '*.*', 'Select JPG or TIFF-Files:', handles.Dir.Input,'MultiSelect','on');
cd(handles.Dir.Image);

% % Eingelesene Files in Listbox darstelle.
set(handles.ImageListbox,'string',cellstr(handles.ImageFiles(1:end,1:end)));
guidata(hObject, handles);

% --- Executes on selection change in ImageListbox.
function ImageListbox_Callback(hObject, eventdata, handles)
% Hints: contents = cellstr(get(hObject,'String')) returns ImageListbox contents as cell array
%        contents{get(hObject,'Value')} returns selected item from ImageListbox
% InputUrl = strcat(handles.Dir.Image,filename);
tic
if(size(get(handles.ImageListbox,'String'),1)==1)
    selectedImages = get(handles.ImageListbox,'String');
    I = imread(char(strcat(handles.Dir.Image,selectedImages)));
else
    k =size(get(handles.ImageListbox,'String'),1);
    selectedImages = handles.ImageFiles([get(handles.ImageListbox,'Value')]);
    I = imread(char(strcat(handles.Dir.Image,selectedImages(end))));
end
axes(handles.preview);
imshow(I);
toc

function checkerboard_w_text_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboard_w_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
if(str2num(get(handles.checkerboard_w_text,'String')) > 10000)
    set(handles.checkerboard_w_text,'String','10000');
end



% --- Executes during object creation, after setting all properties.
function checkerboard_w_text_CreateFcn(hObject, eventdata, handles)
% hObject    handle to checkerboard_w_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called
% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function checkerboard_h_text_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboard_h_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
if(str2num(get(handles.checkerboard_h_text,'String')) > 10000)
    set(handles.checkerboard_h_text,'String','10000');
end

% --- Executes during object creation, after setting all properties.
function checkerboard_h_text_CreateFcn(hObject, eventdata, handles)
% hObject    handle to checkerboard_h_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function checkerboard_px_text_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboard_px_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)


% --- Executes during object creation, after setting all properties.
function checkerboard_px_text_CreateFcn(hObject, eventdata, handles)
% hObject    handle to checkerboard_px_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in checkerboardbtn.
function checkerboardbtn_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboardbtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get Data from Inputfields
w = str2num(get(handles.checkerboard_w_text,'String'));
h = str2num(get(handles.checkerboard_h_text,'String'));
px = str2num(get(handles.checkerboard_px_text,'String'));


create_checkerboard(px,w,h, handles.Dir);
% Set Infotext
set(handles.infotext,'String','Checkerboard created!')





% --- Executes on button press in checkerboardbtn.
function pushbutton6_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboardbtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)



function edit10_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboard_px_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of checkerboard_px_text as text
%        str2double(get(hObject,'String')) returns contents of checkerboard_px_text as a double


% --- Executes during object creation, after setting all properties.
function edit10_CreateFcn(hObject, eventdata, handles)
% hObject    handle to checkerboard_px_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit11_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboard_h_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of checkerboard_h_text as text
%        str2double(get(hObject,'String')) returns contents of checkerboard_h_text as a double


% --- Executes during object creation, after setting all properties.
function edit11_CreateFcn(hObject, eventdata, handles)
% hObject    handle to checkerboard_h_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit12_Callback(hObject, eventdata, handles)
% hObject    handle to checkerboard_w_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of checkerboard_w_text as text
%        str2double(get(hObject,'String')) returns contents of checkerboard_w_text as a double


% --- Executes during object creation, after setting all properties.
function edit12_CreateFcn(hObject, eventdata, handles)
% hObject    handle to checkerboard_w_text (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function noise_w_Callback(hObject, eventdata, handles)
% hObject    handle to noise_w (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of noise_w as text
%        str2double(get(hObject,'String')) returns contents of noise_w as a double
if(str2num(get(handles.noise_w,'String')) > 10000)
    set(handles.noise_w,'String','10000');
end

% InputSize.h = str2num(get(handles.noise_h,'String'));



% --- Executes during object creation, after setting all properties.
function noise_w_CreateFcn(hObject, eventdata, handles)
% hObject    handle to noise_w (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function noise_h_Callback(hObject, eventdata, handles)
% hObject    handle to noise_h (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of noise_h as text
%        str2double(get(hObject,'String')) returns contents of noise_h as a double
if(str2num(get(handles.noise_h,'String')) > 10000)
    set(handles.noise_h,'String','10000');
end


% --- Executes during object creation, after setting all properties.
function noise_h_CreateFcn(hObject, eventdata, handles)
% hObject    handle to noise_h (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function bluenoise_amount_Callback(hObject, eventdata, handles)
% hObject    handle to bluenoise_amount (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of bluenoise_amount as text
%        str2double(get(hObject,'String')) returns contents of bluenoise_amount as a double


% --- Executes during object creation, after setting all properties.
function bluenoise_amount_CreateFcn(hObject, eventdata, handles)
% hObject    handle to bluenoise_amount (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in bluenoise_fft_btn.
function bluenoise_fft_btn_Callback(hObject, eventdata, handles)
% hObject    handle to bluenoise_fft_btn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
set(handles.infotext,'String','Creating Blue Noise FFT Images...')
InputSize.w = str2double(get(handles.noise_w,'String'));
InputSize.h = str2double(get(handles.noise_h,'String'));
FilterVal.amount = str2double(get(handles.bluenoise_amount,'String'));
FilterVal.cutoff = str2double(get(handles.cutoff_fft_edit,'String'));

create_bluenoise_fft(InputSize,handles.Dir,FilterVal);
set(handles.infotext,'String','Blue Noise FFT Images - done!')
cd(handles.Dir.Main);


% --- Executes on button press in bluenoise_gaussian_btn.
function bluenoise_gaussian_btn_Callback(hObject, eventdata, handles)
% hObject    handle to bluenoise_gaussian_btn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
set(handles.infotext,'String','Creating Blue Noise Gaussian Images...')
InputSize.w = str2double(get(handles.noise_w,'String'));
InputSize.h = str2double(get(handles.noise_h,'String'));
FilterVal.amount = str2double(get(handles.bluenoise_amount,'String'));
FilterVal.matrix_x = str2double(get(handles.filtermatrix_x_edit,'String'));
FilterVal.matrix_y = str2double(get(handles.filtermatrix_y_edit,'String'));
FilterVal.sigma = str2double(get(handles.sigma_edit,'String'));

create_bluenoise_gaussian(InputSize, handles.Dir, FilterVal );
set(handles.infotext,'String','Blue Noise Gaussian Images - done!')
cd(handles.Dir.Main);

% --- Executes on button press in selectfolder_btn.
function selectfolder_btn_Callback(hObject, eventdata, handles)
% hObject    handle to selectfolder_btn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
handles.savefolderpath = uigetdir(handles.Dir.Main);
set(handles.savedir_edit,'String',handles.savefolderpath);
handles.Dir.Result = handles.savefolderpath;
text = strcat({'Save directory set to: '},handles.savefolderpath);
set(handles.infotext,'String',text);



function savedir_edit_Callback(hObject, eventdata, handles)
% hObject    handle to savedir_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of savedir_edit as text
%        str2double(get(hObject,'String')) returns contents of savedir_edit as a double


% --- Executes during object creation, after setting all properties.
function savedir_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to savedir_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% guidata(hObject, handles);
% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



% --- Executes on slider movement.
function noiselevel_edit_Callback(hObject, eventdata, handles)
% hObject    handle to noiselevel_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% set text to sliderinput



% --- Executes during object creation, after setting all properties.
function noiselevel_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to noiselevel_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: slider controls usually have a light gray background.
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end



function noiselevel_edit_Callback(hObject, eventdata, handles)
% hObject    handle to noiselevel_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Set Slider to Textinput
if(str2double(get(handles.noiselevel_edit,'String')) > 10)
    set(handles.noiselevel_edit,'String',10);
elseif(str2double(get(handles.noiselevel_edit,'String')) < 0)
    set(handles.noiselevel_edit,'String',0 );
end




% --- Executes during object creation, after setting all properties.
function noiselevel_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to noiselevel_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

% --- Executes on button press in checkall_checkbox.
function checkall_checkbox_Callback(hObject, eventdata, handles)
% hObject    handle to checkall_checkbox (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% set(handles.checkall_checkbox,'Value',1);
if(get(handles.checkall_checkbox,'Value')==1)
    set(handles.cropcheckbox,'Value',1);
    set(handles.ciecamcheckbox,'Value',1);
    set(handles.noisecheckbox,'Value',1);
end

if(get(handles.checkall_checkbox,'Value')==0)
    set(handles.cropcheckbox,'Value',0);
    set(handles.ciecamcheckbox,'Value',0);
    set(handles.noisecheckbox,'Value',0);
end


function videolength_edit_Callback(hObject, eventdata, handles)
% hObject    handle to videolength_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of videolength_edit as text
%        str2double(get(hObject,'String')) returns contents of videolength_edit as a double


% --- Executes during object creation, after setting all properties.
function videolength_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to videolength_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in noisesample_btn.
function noisesample_btn_Callback(hObject, eventdata, handles)
% hObject    handle to noisesample_btn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of noisesample_btn
if(size(get(handles.ImageListbox,'String'),1)==1 && strcmp((get(handles.ImageListbox,'String')),'Select Images') ==0)
    selectedImages = get(handles.ImageListbox,'String');
    create_noiseSamples( handles.Dir,selectedImages(1));
elseif(strcmp((get(handles.ImageListbox,'String')),'Select Images'))
    disp('No Image selected - choose Image to render samples from');
    set(handles.infotext,'String','No Image selected - choose Image to render samples from');
elseif(size(get(handles.ImageListbox,'String'),1)>1)
    selectedImages = handles.ImageFiles([get(handles.ImageListbox,'Value')]);
    create_noiseSamples( handles.Dir,selectedImages(1));
end



function cutoff_fft_edit_Callback(hObject, eventdata, handles)
% hObject    handle to cutoff_fft_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of cutoff_fft_edit as text
%        str2double(get(hObject,'String')) returns contents of cutoff_fft_edit as a double
if(str2double(get(handles. cutoff_fft_edit,'String')) > 300)
    set(handles. cutoff_fft_edit,'String',300);
elseif(str2double(get(handles. cutoff_fft_edit,'String')) < 0)
    set(handles. cutoff_fft_edit,'String',0 );
end


% --- Executes during object creation, after setting all properties.
function cutoff_fft_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to cutoff_fft_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function filtermatrix_y_edit_Callback(hObject, eventdata, handles)
% hObject    handle to filtermatrix_y_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of filtermatrix_y_edit as text
%        str2double(get(hObject,'String')) returns contents of filtermatrix_y_edit as a double


% --- Executes during object creation, after setting all properties.
function filtermatrix_y_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to filtermatrix_y_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function sigma_edit_Callback(hObject, eventdata, handles)
% hObject    handle to sigma_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of sigma_edit as text
%        str2double(get(hObject,'String')) returns contents of sigma_edit as a double


% --- Executes during object creation, after setting all properties.
function sigma_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to sigma_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function filtermatrix_x_edit_Callback(hObject, eventdata, handles)
% hObject    handle to filtermatrix_x_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of filtermatrix_x_edit as text
%        str2double(get(hObject,'String')) returns contents of filtermatrix_x_edit as a double


% --- Executes during object creation, after setting all properties.
function filtermatrix_x_edit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to filtermatrix_x_edit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



% --- Executes on button press in select_noisefolder_btn.
function select_noisefolder_btn_Callback(hObject, eventdata, handles)
% hObject    handle to select_noisefolder_btn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
handles.noisefolderpath = uigetdir(handles.Dir.Main);
set(handles.noisedir_txt,'String',handles.noisefolderpath);
handles.Dir.BlueNoise = handles.noisefolderpath;
text = strcat({'Noise directory set to: '},handles.noisefolderpath);
set(handles.infotext,'String',text);

% --- Executes on button press in runbtn.
function runbtn_Callback(hObject, eventdata, handles)

% Get Radiobuttons and  Data
surroundCiecam.input = get(get(handles.CiecamInputGroup,'SelectedObject'), 'String');
Profile.Input = get(get(handles.InputProfileGroup,'SelectedObject'), 'String');
Profile.Output = get(get(handles.OutputProfileGroup,'SelectedObject'), 'String');
OutputSize.W = str2double(get(handles.Output_Width,'String'));
OutputSize.H = str2double(get(handles.Output_Heigth,'String'));

Cropstyle.cut = get(handles.cropandcut_radio,'Value');
Cropstyle.fill = get(handles.cropandfill_radio,'Value');

NoiseLevel = str2double(get(handles.noiselevel_edit,'String'));
NoiseVideoDuration = str2double(get(handles.videolength_edit,'String'));
Run.Ciecam = get(handles.ciecamcheckbox,'Value');
Run.Crop = get(handles.cropcheckbox,'Value');
Run.Noise = get(handles.noisecheckbox,'Value');
% % zwei fälle:
% es wird nur 1 bild reingeladen, Value = 1, String steht der Name
% es werden mehrere bilder reingeladen aber nur 1 markiert, Value = 1,
% String stehen alle Namen
if(size(get(handles.ImageListbox,'String'),1)==1)
    selectedImages = get(handles.ImageListbox,'String');
else
    selectedImages = handles.ImageFiles([get(handles.ImageListbox,'Value')]);
end
set(handles.infotext,'String','Image processing started');
% check if there are any images selected
if(strcmp(selectedImages, 'Select Images'))
    set(handles.infotext,'String','No images selected!');
    processing = 0;
else
    processing = 1;
end

if(processing ==1)
    main_processing(handles.Dir,selectedImages,Profile,OutputSize,surroundCiecam,NoiseLevel,Cropstyle,NoiseVideoDuration, Run );
    set(handles.infotext,'String','Image processing done!');
else
    
    
end


% --- Executes on button press in selectfolder_btn.
function pushbutton12_Callback(hObject, eventdata, handles)
% hObject    handle to selectfolder_btn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
