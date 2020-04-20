import Vue from 'vue';
import Vuetify from 'vuetify/lib';

import ClinicIcon from "../components/icons/ClinicIcon";

const CUSTOM_ICONS = {
    clinic: { component: ClinicIcon },

};

Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        values: CUSTOM_ICONS,
    },
})
