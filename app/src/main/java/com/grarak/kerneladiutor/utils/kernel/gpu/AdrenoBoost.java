package com.grarak.kerneladiutor.utils.kernel.gpu;
import android.content.Context;

import com.grarak.kerneladiutor.fragments.ApplyOnBootFragment;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.root.Control;

/**
 * Created by callmesuper on 16-9-9.
 */
public class AdrenoBoost {
    private static final String ADRENO_BOOST_PARAMETERS = "/sys/class/kgsl/kgsl-3d0/devfreq";
    private static final String ADRENO_BOOST_LEVEL = ADRENO_BOOST_PARAMETERS + "/adrenoboost";

    public static void setAdrenoBoostLevel(int value, Context context) {
        run(Control.write(String.valueOf(value), ADRENO_BOOST_LEVEL),
                ADRENO_BOOST_LEVEL, context);
    }

    public static int getAdrenoBoostLevel() {
        return Utils.strToInt(Utils.readFile(ADRENO_BOOST_LEVEL));
    }

    public static boolean hasAdrenoBoostLevel() {
        return Utils.existFile(ADRENO_BOOST_LEVEL);
    }

    public static boolean supported() {
        return Utils.existFile(ADRENO_BOOST_LEVEL);
    }

    private static void run(String command, String id, Context context) {
        Control.runSetting(command, ApplyOnBootFragment.GPU, id, context);
    }
}
