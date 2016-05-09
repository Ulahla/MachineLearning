# Prevent Octave from thinking that this
# is a function file:

clear;

# Load the nations.csv file
nations = dlmread("nations.csv", "," , 1,0);

# Wieviele Menschen leben auf der Erde?
habitants = sum(nations(:,7))
# habitants = 6.4823e+09

# Wie hoch ist die durchschnittliche Lebenserwartung?
lifespan = mean(nations(:,5))
# lifepan = 71.690

# Leben lesende Menschen länger? Belegen sie ihre Behauptung mit einem Diagramm.
figure;
plot(nations(:,6), nations(:,5), "xr");
title("Lesende Menschen");
xlabel("literacy");
ylabel("lifespan");

# Plotten sie die durchschnittliche Lebenserwartung (Y-Achse) abhängig vom 
# BIP pro Kopf (X-Achse).
figure;
plot (nations(:,4) ./ nations(:,7) .* 1000000 , nations(:,5), "xr" );
title("Lebenserwartung in Abhängigkeit des BIP");
ylabel("lifespan");
xlabel("gdp per person");
hold;

# Zeichnen sie zusätzlich eine Gerade in den selben plot, die ungefähr den 
# Verlauf der Datenpunkte wiederspiegelt.
[gdpPPMinValue gdpPPMinIndex] = min(nations(:,4) ./ nations(:,7));
minGDP = [gdpPPMinValue  nations(gdpPPMinIndex,5)];
plot(minGDP(:,1) .* 1000000 , minGDP(:,2) ,"og");

[gdpPPMaxValue gdpPPMaxIndex] = max(nations(:,4) ./ nations(:,7));
maxGDP = [gdpPPMaxValue nations(gdpPPMaxIndex,5)];
plot(maxGDP(:,1) .*1000000, maxGDP(:,2) , "og");

#plotten einer Gerade
x = [minGDP(1,1) maxGDP(1,1)];
y = [minGDP(1,2) maxGDP(1,2)];
plot(x .*1000000, y);


# Erstellen sie die passende Geradengleichung dazu und lagern 
# sie diese in eine Funktion aus. y = mx+n == n = y-mx
[m, b] = geradengleichung(x,y);
#defbereich_f = [x(1,1) : 0.01 : x(1,2)];
# wertebereich_y = [y(1,1) : 0.01 : y(1,2)];
# plot(defbereich_f .*1000000, y = m * defbereich_f + b, "r");
# m = 224.73 n = 49.952

# Berechne sie wie gut die Gerade die Datenpunkte approximiert, indem sie 
# den root-mean-square error (RMSE) ausrechnen
# rmse= sqrt(sum((Y .-R).^ 2)/ n)
mGdpPPLifespan = [((nations(:,4)./nations(:,7)).* 1000000)  nations(:,5) ];
plot(mGdpPPLifespan(:,1), y = m * (mGdpPPLifespan(:,1)) ./1000000 + b, "r");
Y = m*mGdpPPLifespan(:,1) ./1000000+b;
n = length(mGdpPPLifespan);
rmse = sqrt(sum((Y .- nations(:,5)).^2)/n)

Y = m .* 3 * mGdpPPLifespan(:,1) ./1000000 + b;
plot(mGdpPPLifespan(:,1), y = m .*3 * (mGdpPPLifespan(:,1)) ./1000000 + b, "b");

rmse = sqrt(sum((Y .- nations(:,5)).^2)/n)