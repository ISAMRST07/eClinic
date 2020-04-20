<template>
    <v-row>
        <v-col cols="12">
            <v-text-field label="Name*" required></v-text-field>
        </v-col>
        <v-col cols="12" sm="6" align-self="end">
            <v-text-field label="Address*"
                          ref="address"
                          :rules="addressRules"
                          :value="clinic.address"
                          @keypress.enter="$emit('mapAddress', $event.target.value)"
                          @focusout="$emit('mapAddress', $event.target.value)"
                          required>
                <v-tooltip bottom slot="append">
                    <template v-slot:activator="{ on }">
                        <v-btn icon v-on="on"
                               @click="$emit('mapAddress', $refs.address.internalValue)"
                               class="d-none d-md-flex"
                        >
                            <v-icon>mdi-map-marker</v-icon>
                        </v-btn>
                        <v-btn icon v-on="on"
                               class="d-flex d-md-none"
                               @click="$emit('mapDialog')"
                        >
                            <v-icon>mdi-map</v-icon>
                        </v-btn>
                    </template>
                    <span>Show on the map</span>
                </v-tooltip>
            </v-text-field>
        </v-col>
        <v-col cols="12" sm="6" align-self="end">
            <v-text-field label="Coordinates"
                          :value="clinic.coordinates | formatCoords"
                          disabled
            >
            </v-text-field>
        </v-col>
        <v-col cols="12">
            <v-textarea
                    counter="256"
                    outlined
                    label="Description"
                    rows="10"
                    no-resize
                    :rules="rules"
            ></v-textarea>
        </v-col>
    </v-row>
</template>

<script>

    import {mapState} from "vuex";

    export default {
        name: "AddClinicForm",
        data: () => ({
            rules: [v=> !!v || "* Required.", v => v?.length <= 255 || 'Max 256 characters.'],
            addressRules: [value => !!value || 'Valid address required.']
        }),
        computed: {
            ...mapState('clinics/addClinic', ['clinic'])
        },
        filters: {
            formatCoords(value) {
                if (!value) return '';
                return `(${value.lat.toFixed(6)}, ${value.lng.toFixed(6)})`;
            }
        }
    }
</script>

<style scoped>

</style>
