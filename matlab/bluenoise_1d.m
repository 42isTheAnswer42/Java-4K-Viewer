function y = bluenoise_1d(N,FilterVal)

% Calculate cutoff frequencie by n and innput
cutoffFreq = N*FilterVal.cutoff/N;
% generate uniformly distributed white noise
x  = unifrnd(0, 1,1,N);

% FFT
Xn = fft(x);
% prepare a vector for f multiplication

NumUniquePts = N/2 + 1;
n = 1:NumUniquePts;
n = sqrt(n);

% Set filtermatrix to cutoff frequencie
if(cutoffFreq==0)
    
else
    n(1:cutoffFreq) = 0;
end
X = Xn;

% multiplicate the left half of the spectrum so the power spectral density
% is proportional to the frequency by factor f, i.e. the
% amplitudes are proportional to sqrt(f)
X(1:NumUniquePts) = X(1:NumUniquePts).*n;

% prepare a right half of the spectrum - a copy of the left one,
% except the DC component and Nyquist frequency - they are unique
X(NumUniquePts+1:N) = real(X(N/2:-1:2)) -1i*imag(X(N/2:-1:2));

% IFFT
y = ifft(X);

% prepare output vector y
y = real(y(1, 1:N));

% ensure unity standard deviation and zero mean value
y = y - mean(y);
yrms = sqrt(mean(y.^2));
y = y/yrms;

end