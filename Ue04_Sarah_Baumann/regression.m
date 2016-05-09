## Copyright (C) 2016 Sarah Baumann
## 
## This program is free software; you can redistribute it and/or modify it
## under the terms of the GNU General Public License as published by
## the Free Software Foundation; either version 3 of the License, or
## (at your option) any later version.
## 
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Public License for more details.
## 
## You should have received a copy of the GNU General Public License
## along with this program.  If not, see <http://www.gnu.org/licenses/>.

## -*- texinfo -*- 
## @deftypefn {Function File} {@var{retval} =} regression (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Sarah Baumann <sarahbaumann@Sarah-Baumanns-MacBook-Pro.local>
## Created: 2016-04-28

function [theta_best, rmse_best] = regression (alpha, m, theta, iteration, mpg_predict, mpg_norm, norm)
  rmse = 100;
  for i = 0 : iteration
    diff = [mpg_predict .- mpg_norm];
    delta_theta = norm' * diff;
    delta_theta_strich = (alpha/m) * delta_theta;
    #delta_theta_strich = delta_theta
    theta_new = theta' - delta_theta_strich
    rmse_temp = sqrt(sum((mpg_predict .- mpg_norm).^2)/ length(mpg_norm));
    if(rmse > rmse_temp)
      theta_best = theta_new;
      rmse_best = rmse_temp;
    endif
  endfor
endfunction