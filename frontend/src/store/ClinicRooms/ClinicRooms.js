import Vue from 'vue';

export default {
    namespaced: true,
    state: {
        clinicRooms: [],
    },
    mutations: {
        setAllClinicRooms(state, clinicRooms){
            Vue.set(state, 'clinicRooms', clinicRooms);
        },
        addClinicRoom(state, clinicRoom) {
            state.clinicRooms.push(clinicRoom);
        },
        deleteClinicRoom(state, clinicRoom) {
            let index = state.clinicRooms.findIndex(c => c.id === clinicRoom.id);
            state.clinicRooms.splice(index, 1);
        },
        updateClinicRoom(state, clinicRoom) {
            state.clinicRooms = [
                ...state.clinicRooms.filter(c => c.id !== clinicRoom.id),
                clinicRoom
            ]
        }
    },
    actions: {
        async getClinicRooms({rootState, commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/clinicroom',
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('setAllClinicRooms', res.data);
            } catch (err) {
                console.error(err);
            }
        },
        async addClinicRoomApi({rootState, commit}, clinicRoom) {
            try {
                let {data: added} = await Vue.prototype.$axios.post('/api/clinicroom', clinicRoom,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                console.log(clinicRoom.name);
                commit('addClinicRoom', added);
            } catch (err) {
                console.error(err);
            }
        },
        async deleteRoomApi({rootState, commit}, clinicRoom) {
            try {
                let res = await Vue.prototype.$axios.delete(`/api/clinicroom/${clinicRoom.id}`,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('deleteClinicRoom', clinicRoom);
            } catch (err) {
                console.error(err);
            }
        },
        async updateClinicRoomApi({rootState, commit}, clinicRoom) {
            console.log(clinicRoom);
            try {
                let {data: modified} = await Vue.prototype.$axios.put('/api/clinicroom', clinicRoom,
                    {headers: {"Authorization": 'Bearer ' + rootState.auth.token} });
                commit('updateClinicRoom', modified);
            } catch (err) {
                console.error(err);
            }
        },
    },
};
