<template>
    <div @scroll="scroll">
        <modify-clinic-dialog
            v-model="addDialog"/>
        <v-btn
                v-if="role === adminCode"
                fab
                dark
                large
                color="red"
                :class="{'fab-br': true, down: scrolledDown}"
                @click.stop="addDialog = true"
        >
            <v-icon> mdi-plus</v-icon>
        </v-btn>
    </div>
</template>

<script>
    import ModifyClinicDialog from "./ModifyClinicDialog";
    import {mapState} from "vuex";
    import {ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "AddClinic",
        components: {ModifyClinicDialog},
        data: () => ({
            addDialog: false,
            adminCode: ClinicalCenterAdmin.code,
            scrolledDown: false
        }),
        computed: {
            ...mapState('auth', ['role']),
            ...mapState('clinics/readClinics', ['clinics']),
        },
        watch: {
            clinics() {
                this.checkSize();
            }
        },
        methods: {
            scroll () {
                window.onscroll = () => {
                    this.checkSize();
                }
            },
            checkSize() {
                this.scrolledDown = Math.ceil(
                    Math.max(window.pageYOffset,
                        document.documentElement.scrollTop,
                        document.body.scrollTop) + window.innerHeight)
                    >= document.documentElement.offsetHeight;
            }
        },
        mounted() {
            this.scroll();
        }
    }
</script>

<style scoped>
    .down {
        right: 50%;
        opacity: 60%;
    }
</style>
