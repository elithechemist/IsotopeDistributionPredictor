package com.chemicalformulas.chemicalformulasfx;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public abstract class ChemicalFormula {
    protected static final String[] ATOMIC_SYMBOLS = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne",
            "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca",
            "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn",
            "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr",
            "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn",
            "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
            "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb",
            "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg",
            "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th",
            "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm",
            "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds",
            "Rg", "Cn", "Nh", "Fl", "Mc", "Lv", "Ts", "Og"};
    private static final double[] MOLECULAR_WEIGHT = {1.008, 4.002602, 6.94, 9.0121831, 10.81, 12.011, 14.007, 15.999, 18.998403163, 20.1797,
            22.98976928, 24.305, 26.9815384, 28.085, 30.973761998, 32.06, 35.45, 39.95, 39.0983, 40.078,
            44.955, 47.867, 50.9415, 51.9961, 54.938043, 55.845, 58.933194, 58.6934, 63.546, 65.38,
            69.723, 72.630, 74.921595, 78.971, 79.904, 83.798, 85.4678, 87.62, 88.905838, 91.224,
            92.90637, 95.95, 97, 101.07, 102.90549, 106.42, 107.8682, 112.414, 114.818, 118.710,
            121.760, 127.60, 126.90447, 131.293, 132.90545196, 137.327, 138.90547, 140.116, 140.90766, 144.242,
            145, 150.36, 151.964, 157.25, 158.925, 162.500, 164.930, 167.259, 168.934, 173.045,
            174.9668, 178.486, 180.94788, 183.84, 186.207, 190.23, 192.217, 195.084, 196.966570, 200.592,
            204.38, 207.2, 208.980, 209, 210, 222, 223, 226, 227, 232.0377,
            231.03588, 238.02891, 237, 244, 243, 247, 247, 251, 252, 257,
            258, 259, 262, 267, 270, 269, 270, 270, 278, 281,
            281, 285, 286, 289, 289, 293, 293, 294};
    private static final double[] MOLECULAR_WEIGHT_UNCERTAINTY = {0.0005, 0.000002, 0.005, 0.0000005, 0.005, 0.0005, 0.0005, 0.0005, 0.000000005, 0.0006,
            0.00000002, 0.0005, 0.0000003, 0.0005, 0.000000005, 0.005, 0.005, 0.005, 0.0001, 0.004,
            0.0005, 0.001, 0.0001, 0.0006, 0.000002, 0.002, 0.000003, 0.0004, 0.003, 0.02,
            0.001, 0.08, 0.000006, 0.008, 0.0005, 0.002, 0.0003, 0.01, 0.000002, 0.002,
            0.00001, 0.01, 0, 0.02, 0.00002, 0.01, 0.0002, 0.004, 0.001, 0.07,
            0.01, 0.3, 0.00003, 0.006, 0.00000006, 0.007, 0.00007, 0.001, 0.00001, 0.003,
            0, 0.02, 0.001, 0.03, 0.0005, 0.1, 0.005, 0.003, 0.0005, 0.010,
            0.0001, 0.006, 0.00002, 0.01, 0.001, 0.03, 0.002, 0.009, 0.00004, 0.003,
            0.005, 0.1, 0.005, 0, 0, 0, 0, 0, 0, 0.0004,
            0.00001, 0.00003, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[][] MASS_NUMBER = {{1, 2, 3}, {3, 4}, {6, 7}, {9}, {10, 11}, {12, 13, 14}, {14, 15}, {16, 17, 18}, {19}, {20, 21, 22},
            {23}, {24, 25, 26}, {27}, {28, 29, 30}, {31}, {32, 33, 34, 36}, {35, 37}, {36, 38, 40}, {39, 40, 41}, {40, 42, 43, 44, 46, 48},
            {45}, {46, 47, 48, 49, 50}, {50, 51}, {50, 52, 53, 54}, {55}, {54, 56, 57, 58}, {59}, {58, 60, 61, 62, 64}, {63, 65}, {64, 66, 67, 68, 70},
            {69, 71}, {70, 72, 73, 74, 76}, {75}, {74, 76, 77, 78, 80, 82}, {79, 81}, {78, 80, 82, 83, 84, 86}, {85, 87}, {84, 86, 87, 88}, {89}, {90, 91, 92, 94, 96},
            {93}, {92, 94, 95, 96, 97, 98, 100}, {98}, {96, 98, 99, 100, 101, 102, 104}, {103}, {102, 104, 105, 106, 108, 110}, {107, 109}, {106, 108, 110, 111, 112, 113, 114, 116}, {113, 115}, {112, 114, 115, 116, 117, 118, 119, 120, 122, 124},
            {121, 123}, {120, 122, 123, 124, 125, 126, 128, 130}, {127}, {124, 126, 128, 129, 130, 131, 132, 134, 136}, {133}, {130, 132, 134, 135, 136, 137, 138}, {138, 139}, {136, 138, 140, 142}, {141}, {142, 143, 144, 145, 146, 148, 150},
            {145}, {144, 147, 148, 149, 150, 152, 154}, {151, 153}, {152, 154, 155, 156, 157, 158, 160}, {159}, {156, 158, 160, 161, 162, 163, 164}, {165}, {162, 164, 166, 167, 168, 170}, {169}, {168, 170, 171, 172, 173, 174, 176},
            {175, 176}, {174, 176, 177, 178, 179, 180}, {180, 181}, {180, 182, 183, 184, 186}, {185, 187}, {184, 186, 187, 188, 189, 190, 192}, {191, 193}, {190, 192, 194, 195, 196, 198}, {197}, {196, 198, 199, 200, 201, 202, 204},
            {203, 205}, {204, 206, 207, 208}, {209}, {209}, {210}, {222}, {223}, {226}, {227}, {232},
            {231}, {234, 235, 238}, {237}, {244}, {243}, {247}, {247}, {251}, {252}, {257},
            {258}, {259}, {262}, {263}, {262}, {266}, {264}, {269}, {268}, {272},
            {272}, {277}, {289}, {289}, {293}};
    private static final int[] NUMBER_ISOTOPES = {3, 2, 2, 1, 2, 3, 2, 3, 1, 3,
            1, 3, 1, 3, 1, 4, 2, 3, 3, 6,
            1, 5, 2, 4, 1, 4, 1, 5, 2, 5,
            2, 5, 1, 6, 2, 6, 2, 4, 1, 5,
            1, 7, 1, 7, 1, 6, 2, 8, 2, 10,
            2, 8, 1, 9, 1, 7, 2, 4, 1, 7,
            1, 7, 2, 7, 1, 7, 1, 6, 1, 7,
            2, 6, 2, 5, 2, 7, 2, 6, 1, 7,
            2, 4, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 3, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1};
    private static final double[][] EXACT_MASS = {{1.007825, 2.014102, 3.016049}, {3.016029, 4.002603}, {6.015122, 7.016004}, {9.012182}, {10.012937, 11.009305}, {12.000000, 13.003355, 14.003242}, {14.003074, 15.000109}, {15.994915, 16.999132, 17.999160}, {18.998403}, {19.992440, 20.993847, 21.991386},
            {22.989770}, {23.985042, 24.985837, 25.982593}, {26.981538}, {27.976927, 28.976495, 29.973770}, {30.973762}, {31.972071, 32.971458, 33.967867, 35.967081}, {34.968853, 36.965903}, {35.967546, 37.962732, 39.962383}, {38.963707, 39.963999, 40.961826}, {39.962591, 41.958618, 42.958767, 43.955481, 45.953693, 47.952534},
            {44.955910}, {45.952629, 46.951764, 47.947947, 48.947871, 49.944792}, {49.947163, 50.943964}, {49.946050, 51.940512, 52.940654, 53.938885}, {54.938050}, {53.939615, 55.934942, 56.935399, 57.933280}, {58.933200}, {57.935348, 59.930791, 60.931060, 61.928349, 63.927970}, {62.929601, 64.927794}, {63.929147, 65.926037, 66.927131, 67.924848, 69.925325},
            {68.925581, 70.924705}, {69.924250, 71.922076, 72.923459, 73.921178, 75.921403}, {74.921596}, {73.922477, 75.919214, 76.919915, 77.917310, 79.916522, 81.916700}, {78.918338, 80.916291}, {77.920386, 79.916378, 81.913485, 82.914136, 83.911507, 85.910610}, {84.911789, 86.909183}, {83.913425, 85.909262, 86.908879, 87.905614}, {88.905848}, {89.904704, 90.905645, 91.905040, 93.906316, 95.908276},
            {92.906378}, {91.906810, 93.905088, 94.905841, 95.904679, 96.906021, 97.905408, 99.907477}, {97.907216}, {95.907598, 97.905287, 98.905939, 99.904220, 100.905582, 101.904350, 103.905430}, {102.905504}, {101.905608, 103.904035, 104.905084, 105.903483, 107.903894, 109.905152}, {106.905093, 108.904756}, {105.906458, 107.904183, 109.903006, 110.904182, 111.902757, 112.904401, 113.903358, 115.904755}, {112.904061, 114.903878}, {111.904821, 113.902782, 114.903346, 115.901744, 116.902954, 117.901606, 118.903309, 119.902197, 121.903440, 123.905275},
            {120.903818, 122.904216}, {119.904020, 121.903047, 122.904273, 123.902819, 124.904425, 125.903306, 127.904461, 129.906223}, {126.904468}, {123.905896, 125.904269, 127.903530, 128.904779, 129.903508, 130.905082, 131.904154, 133.905395, 135.907220}, {132.905447}, {129.906310, 131.905056, 133.904503, 134.905683, 135.904570, 136.905821, 137.905241}, {137.907107, 138.906348}, {135.907144, 137.905986, 139.905434, 141.909240}, {140.907648}, {141.907719, 142.909810, 143.910083, 144.912569, 145.913112, 147.916889, 149.920887},
            {144.912744}, {143.911995, 146.914893, 147.914818, 148.917180, 149.917271, 151.919728, 153.922205}, {150.919846, 152.921226}, {151.919788, 153.920862, 154.922619, 155.922120, 156.923957, 157.924101, 159.927051}, {158.925343}, {155.924278, 157.924405, 159.925194, 160.926930, 161.926795, 162.928728, 163.929171}, {164.930319}, {161.928775, 163.929197, 165.930290, 166.932045, 167.932368, 169.935460}, {168.934211}, {167.933894, 169.934759, 170.936322, 171.936378, 172.938207, 173.938858, 175.942568},
            {174.940768, 175.942682}, {173.940040, 175.941402, 176.943220, 177.943698, 178.945815, 179.946549}, {179.947466, 180.947996}, {179.946706, 181.948206, 182.950224, 183.950933, 185.954362}, {184.952956, 186.955751}, {183.952491, 185.953838, 186.955748, 187.955836, 188.958145, 189.958445, 191.961479}, {190.960591, 192.962924}, {189.959930, 191.961035, 193.962664, 194.964774, 195.964935, 197.967876}, {196.966552}, {195.965815, 197.966752, 198.968262, 199.968309, 200.970285, 201.970626, 203.973476},
            {202.972329, 204.974412}, {203.973029, 205.974449, 206.975881, 207.976636}, {208.980383}, {208.982416}, {209.987131}, {222.017570}, {223.019731}, {226.025403}, {227.027747}, {232.038050},
            {231.035879}, {234.040946, 235.043923, 238.050783}, {237.048167}, {244.064198}, {243.061373}, {247.070347}, {247.070299}, {251.079580}, {252.082972}, {257.095099},
            {258.098425}, {259.101024}, {262.109692}, {263.118313}, {262.011437}, {266.012238}, {264.012496}, {269.001341}, {268.001388},
            {272.001463}, {272.001535}, {277}, {289}, {289}, {293}};
    private static final double[][] ABUNDANCE = {{99.9885, 0.0115, 0.0000}, {0.000137, 99.999863}, {7.59, 92.41}, {100}, {19.9, 80.1}, {98.93, 1.07, 0.00}, {99.632, 0.368}, {99.757, 0.038, 0.205}, {100}, {90.48, 0.27, 9.25},
            {100}, {78.99, 10.00, 11.01}, {100}, {92.2297, 4.6832, 3.0872}, {100}, {94.93, 0.76, 4.29, 0.02}, {75.78, 24.22}, {0.3365, 0.0632, 99.6003}, {93.2581, 0.0117, 6.7302}, {96.941, 0.647, 0.135, 2.086, 0.004, 0.187},
            {100}, {8.25, 7.44, 73.72, 5.41, 5.18}, {0.250, 99.750}, {4.345, 83.789, 9.501, 2.365}, {100}, {5.845, 91.754 , 2.119, 0.282 }, {100}, {68.0769, 26.2231, 1.1399, 3.6345, 0.9256}, {69.17, 30.83}, {48.63, 27.90, 4.10, 18.75, 0.62},
            {60.108, 39.892}, {20.84, 27.54, 7.73, 36.28, 7.61}, {100}, {0.89, 9.37, 7.63, 23.77, 49.61, 8.73}, {50.69, 49.31}, {0.35, 2.28, 11.58, 11.49, 57.00, 17.30}, {72.17, 27.83}, {0.56, 9.86, 7.00, 82.58}, {100}, {51.45, 11.22, 17.15, 17.38, 2.80},
            {100}, {14.84, 9.25, 15.92, 16.68, 9.55, 24.13, 9.63}, {100}, {5.54, 1.87, 12.76, 12.60, 17.06, 31.55, 18.62}, {100}, {1.02, 11.14, 22.33, 27.33, 26.46, 11.72}, {51.839, 48.161}, {1.25, 0.89, 12.49, 12.80, 24.13, 12.22, 28.73, 7.49}, {4.29, 95.71}, {0.97, 0.66, 0.34, 14.54, 7.68, 24.22, 8.59, 32.58, 4.63, 5.79},
            {57.21, 42.79}, {0.09, 2.55, 0.89, 4.74, 7.07, 18.84, 31.74, 34.08}, {100}, {0.09, 0.09, 1.92, 26.44, 4.08, 21.18, 26.89, 10.44, 8.87}, {100}, {0.106, 0.101, 2.417, 6.592, 7.854, 11.232, 71.698}, {0.090, 99.910}, {0.185, 0.251, 88.450, 11.114}, {100}, {27.2, 12.2, 23.8, 8.3, 17.2, 5.7, 5.6},
            {100}, {3.07, 14.99, 11.24, 13.82, 7.38, 26.75, 22.75}, {47.81, 52.19}, {0.20, 2.18, 14.80, 20.47, 15.65, 24.84, 21.86}, {100}, {0.06, 0.10, 2.34, 18.91, 25.51, 24.90, 28.18}, {100}, {0.14, 1.61, 33.61, 22.93, 26.78, 14.93}, {100}, {0.13, 3.04, 14.28, 21.83, 16.13, 31.83, 12.76},
            {97.41, 2.59}, {0.16, 5.26, 18.60, 27.28, 13.62, 35.08}, {0.012, 99.988}, {0.12, 26.50, 14.31, 30.64, 28.43}, {37.40, 62.60}, {0.02, 1.59, 1.96, 13.24, 16.15, 26.26, 40.78 }, {37.3, 62.7}, {0.014, 0.782, 32.967, 33.832, 25.242, 7.163}, {100}, {0.15, 9.97, 16.87, 23.10, 13.18, 29.86, 6.87},
            {29.524, 70.476}, {1.4, 24.1, 22.1, 52.4}, {100}, {100}, {100}, {100}, {100}, {100}, {100}, {100},
            {100}, {0.0055, 0.7200, 99.2745}, {100}, {100}, {100}, {100}, {100}, {100}, {100}, {100},
            {100}, {100}, {100}, {100}, {100}, {100}, {100}, {100}, {100}, {100},
            {100}, {100}, {100}, {100}, {100}};
    protected Integer[] elementsArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0};
    // This would be our example of methods overloading by the way...
    public ChemicalFormula(String chemicalFormulaString) {
        this.elementsArray = toArray(chemicalFormulaString, elementsArray);
    }
    public ChemicalFormula() {
        this("");
    }
    @Override
    public String toString() {
        String tempString = "";
        if(elementsArray[0] == -1) {
            tempString = "Not a valid molecular formula";
        }
        else {
            for (int i = 0; i < 118; i++) {
                if (elementsArray[i] != 0) {
                    tempString += "{" + ATOMIC_SYMBOLS[i] + "_" + elementsArray[i] + "}";
                }
            }
        }
        return tempString;
    }
    public Integer[] getElementsArray() {
        return Arrays.copyOf(elementsArray, elementsArray.length);
    }
    protected abstract Integer[] toArray(String chemicalFormulaString, Integer[] elementArray);
    private BigDecimal[] molecularWeightBigDecimal() {
        double molecularWeight = 0;
        double uncertainty = 0;
        for(int i = 0; i < 118; i++) {
            molecularWeight += elementsArray[i] * MOLECULAR_WEIGHT[i];
            uncertainty += elementsArray[i] * MOLECULAR_WEIGHT_UNCERTAINTY[i];
        }
        BigDecimal bd = new BigDecimal(uncertainty);
        BigDecimal roundedBd = bd.round(new MathContext(2));
        BigDecimal mwBd = new BigDecimal(molecularWeight);
        int scaleMw = (int)Math.log10(mwBd.doubleValue());
        int scaleError;
        double preScaleError = Math.log10(roundedBd.doubleValue());
        if(preScaleError < 0) {
            scaleError = (int)(-1 * Math.ceil(Math.abs(preScaleError)));
        }
        else {
            scaleError = (int)preScaleError;
        }
        BigDecimal roundedMwBd = mwBd.round(new MathContext(scaleMw - scaleError + 2));

        BigDecimal[] bigDecimalArray = {roundedMwBd, roundedBd};
        return bigDecimalArray;
    }

    public String molecularWeightString() {
        String s = "";
        if(elementsArray[0] == -1) {
            s = "Not a valid molecular formula";
        }
        else {
            BigDecimal[] bd = molecularWeightBigDecimal();
            s = bd[0] + " ± " + bd[1] + " g/mol";
        }
        return s;
    }
}
