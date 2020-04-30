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
        async getClinicRooms({commit}) {
            try {
                let res = await Vue.prototype.$axios.get('/api/clinicroom');
                commit('setAllClinicRooms', res.data);
            } catch (err) {
                console.error(err);
            }
        },
        async addClinicRoomApi({commit}, clinicRoom) {
            try {
                let {data: added} = await Vue.prototype.$axios.post('/api/clinicroom', clinicRoom);
                console.log(clinicRoom.name);
                commit('addClinicRoom', added);
            } catch (err) {
                console.error(err);
            }
        },
        async deleteRoomApi({commit}, clinicRoom) {
            try {
                let res = await Vue.prototype.$axios.delete(`/api/clinicroom/${clinicRoom.id}`);
                commit('deleteClinicRoom', clinicRoom);
            } catch (err) {
                console.error(err);
            }
        }
    },
};
