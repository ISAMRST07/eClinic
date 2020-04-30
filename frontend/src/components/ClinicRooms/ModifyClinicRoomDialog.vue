<template>
    <v-dialog
            :value="value"
            @input="$emit('input', false)"
            persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span v-if="mode === 'add'" class="headline">Add a clinic room</span>
                <span v-else class="headline">Update clinic room</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field label="Name*" required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <clinic-selection v-model="selectedClinic"/>
                        </v-col>
                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                <v-btn color="blue darken-1" v-if="mode === `add`" text @click="addClinicRoom">Add</v-btn>
                <v-btn color="blue darken-1" v-else text @click="dialog = false">Update</v-btn>

            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import ClinicSelection from "../Clinics/ClinicSelection";
    export default {
        name: "ModifyClinicRoomDialog",
        components: {ClinicSelection},
        data: () => ({
            selectedClinic: null
        }),
        props: {
            clinic: null,
            value: false,
            mode: 'add'
        },
        methods: {
            addClinicRoom() {

            },
        }
    }
</script>

<style scoped>

</style>
