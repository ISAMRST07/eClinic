const AddClinicModule = {
    namespaced: true,
    state: {
        clinic: {
            name: String,
            description: String,
            address: null,
            coordinates: null
        }
    },
    mutations: {
        updateClinic(state, newParts) {
            state.clinic = Object.assign({}, state.clinic, newParts);
        }
    },
    actions: {

    },
};

const ClinicModules = {
    namespaced: true,
    modules: {
        addClinic: AddClinicModule
    }
};

export default ClinicModules;
