function [y_out, rmse] = polynomZweitenGerades(wieoft, x_in, y_in, n)  rmse = 100;  for j = 1 : 1000    yMax = (max(y_in));    yMin = (min(y_in));    k = rand(1,(wieoft+1));    k .-= 0.5;    k .*= 5;    y = 0;    for i = 0 : wieoft      y .+= k(1,(i+1)) * (x_in .^ i);    endfor    y_denorm = y .* (yMax - yMin) + yMin;    temp = sqrt(sum((y_denorm .- y_in).^2)/n);    if (temp < rmse)      rmse = temp;      y_out = y_denorm;      koeffizienten = k;    endif  endfor  koeffizienten  rmseendfunction