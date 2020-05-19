<template>
    <v-container fluid class="ma-2">
        <v-form ref="form" @submit.prevent="search">
            <v-row align="center">
                <v-col cols="12" sm="3" md="2">
                    <span class="headline">Clinics</span>
                </v-col>
                <v-col cols="12" sm="4" md="3">
                    <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="date"
                            transition="scale-transition"
                            offset-y
                            max-width="290px"
                            min-width="290px"
                    >
                        <template v-slot:activator="{ on }">
                            <v-text-field
                                    v-model="date"
                                    label="Appointment date"
                                    prepend-icon="mdi-calendar"
                                    readonly
                                    v-on="on"
                            ></v-text-field>
                        </template>
                        <v-date-picker
                                v-model="date"
                                type="date"
                                no-title
                                scrollable
                                :min="today"
                        >
                            <v-spacer></v-spacer>
                            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                        </v-date-picker>
                    </v-menu>
                </v-col>
                <v-col cols="12" sm="4" md="3">
                    <intervention-type-selection v-model="type"></intervention-type-selection>
                </v-col>

                <v-col cols="6" sm="2" lg="1" class="text-center">
                    <v-btn color="primary" type="submit">
                        Search
                    </v-btn>
                </v-col>
                <v-col cols="6" sm="2" lg="1">
                    <v-btn text @click="reset">
                        Reset
                    </v-btn>
                </v-col>
            </v-row>
        </v-form>
    </v-container>
</template>

<script>
    import InterventionTypeSelection from "../InterventionType/InterventionTypeSelection";
    import JSOG from 'jsog';
    export default {
        name: "ClinicSearch",
        components: {InterventionTypeSelection},
        data: () => ({
            menu: false,
            date: null,
            type: null
        }),
        methods: {
            search() {
                this.$emit('searched', {date: this.date, interventionType: this.type.id});
            },
            reset() {
                this.$emit('reset');
                this.$refs.form.reset();
            }
        },
        computed: {
            today() {
                let tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds
                return (new Date(Date.now() - tzoffset)).toISOString().slice(0, -1);
            }
        }
    }
</script>

<style scoped>

</style>
