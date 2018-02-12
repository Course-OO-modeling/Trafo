package services;

import params.EllipsoidParms;

/**
 * @class CalculationService
 * @author Daffy
 * @date 07.11.2004
 * @brief Created by IntelliJ IDEA. Formulae: Heck, B., Rechenverfahren und Auswertemodelle der Landesvermessung, Wichmann, 3. ed., 2003. Chapter 7.
 * @remark updated header on 2018-01-06 by Yunhao Huang
 */
public class CalculationService {

    public static double meridianLength(double latitude, EllipsoidParms ell) {

        final int NUMAX = 10, MUMAX = 10;
        int nu, mu;
        double alfq =0., sh = 0., cbr, length;
        double alf[] = new double[NUMAX + 1], beta[] = new double[(NUMAX + 1) * 2];
        double gam[][] = new double[(NUMAX + 1) * 2][NUMAX + 1];

        alf[0] = 1.;
        gam[0][1] = -0.75 * ell.getEs2();
        
        for (nu = 0; nu <= (NUMAX - 1); nu++) { // see Heck formulae 7.28a,b
            alf[nu + 1] = -(1. - 0.25 / (Math.pow((nu + 1), 2))) * ell.getEs2() * alf[nu];
            for (mu = 1; mu <= (MUMAX - 1); mu++) {
                gam[2 * (nu + 1)][1] = -(1. - 1. / (nu + 2)) * (1. + 0.5 / (nu + 2)) * ell.getEs2() * gam[2 * nu][1];
                gam[2 * nu][mu + 1] = -(1. - 0.25 / Math.pow((nu + mu + 1), 2)) * ell.getEs2() * gam[2 * nu][mu];
            }
        }

        for (nu = 0; nu <= NUMAX; nu++) {
            for (mu = 1; mu <= MUMAX; mu++)
                beta[2 * nu] = beta[2 * nu] + gam[2 * nu][mu];
            beta[2 * nu] = ell.getC() * beta[2 * nu];
            alfq = alfq + alf[nu];
        }

        alfq = ell.getC() * alfq;
        /* Length of meridian see Heck 7.27 */
        length = alfq * latitude;
        cbr = Math.cos(latitude) * Math.cos(latitude);
        for (nu = 0; nu <= NUMAX; nu++)
            sh = sh + beta[2 * nu] * Math.pow(cbr, nu);

        return length + Math.sin(latitude) * Math.cos(latitude) * sh;
    } // end MeridianLength
    
}
