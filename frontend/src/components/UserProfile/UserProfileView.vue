<template>
    <div>
        <v-container>
            <v-row>
                <v-col>
                    <v-card class="mt-5" outlined>
                        <v-row>
                            <v-col cols="12" class="pt-10 text-center">
                                <v-avatar class="ma-auto" color="primary" size="110">
                                    <span class="white--text display-3">{{ avatarInitials }}</span>
                                </v-avatar>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" class="text-center pb-0">
                                <span class="display-1">
                                    {{ greetingMessages.welcome }}
                                </span>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12" class="text-center pt-0">
                                <span class="text-center subtitle-1 grey--text text--darken-3">
                                    {{ greetingMessages.subtitle }}
                                </span>
                            </v-col>
                        </v-row>
                    </v-card>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" sm="6" md="8">
                    <v-card outlined min-height="370">
                        <v-card-title>
                            <span class="headline">Personal info</span>
                        </v-card-title>
                        <v-card-subtitle>
                            <span class="subtitle-1">Here's what we know about you.</span>
                        </v-card-subtitle>
                        <v-list two-line>
                            <profile-info-item
                                    :content="`${profile.name} ${profile.surname}`"
                                    subtitle="Name"
                                    @click="nameDialog = true"
                            ></profile-info-item>
                            <profile-info-item
                                    :content="profile.personalID"
                                    subtitle="Personal ID"
                                    disabled
                            ></profile-info-item>
                            <profile-info-item
                                    :content="`${profile.address}, ${profile.city}, ${profile.country}`"
                                    subtitle="Address, city and country"
                                    @click="addressDialog = true"
                            ></profile-info-item>
                            <profile-info-item
                                    :content="profile.phoneNumber"
                                    subtitle="Phone number"
                                    @click="phoneNumberDialog = true"
                            ></profile-info-item>

                        </v-list>
                    </v-card>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                    <v-card outlined min-height="370">
                        <v-card-title>
                            <span class="headline">Security</span>
                        </v-card-title>
                        <v-card-subtitle>
                            <v-row justify="center">
                                <v-col cols="3" align-self="center">
                                    <v-icon class="d-flex" x-large>
                                        mdi-key-variant
                                    </v-icon>
                                </v-col>
                                <v-col cols="9">
                                    <span class="subtitle-1">
                                        You can view your email and change your password here.
                                    </span>
                                </v-col>
                            </v-row>
                        </v-card-subtitle>
                        <v-list two-line>
                            <profile-info-item
                                    :content="profile.email"
                                    subtitle="Email"
                                    disabled
                            ></profile-info-item>
                            <v-divider></v-divider>
                            <v-list-item link @click="passwordDialog = true">
                                <v-list-item-content>
                                    <v-list-item-title>
                                        ••••••••
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        Password
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-action>
                                    <v-icon>
                                        mdi-chevron-right
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                            <profile-info-item
                                    :content="lastChanged"
                                    subtitle="Last time the password was changed"
                                    disabled
                            ></profile-info-item>
                        </v-list>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
        <name-dialog v-model="nameDialog" :user="profile" @modify="updateProfile"></name-dialog>
        <address-dialog v-model="addressDialog" :user="profile" @modify="updateProfile"></address-dialog>
        <phone-number-dialog v-model="phoneNumberDialog" :user="profile" @modify="updateProfile"></phone-number-dialog>
        <password-dialog
                v-model="passwordDialog"
                :user="profile"
                @passwordChange="passwordChanged"
                :personal="profile.id === $store.state.auth.user.id"
        ></password-dialog>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import ProfileInfoItem from "./ProfileInfoItem";
    import NameDialog from "./NameDialog";
    import AddressDialog from "./AddressDialog";
    import PhoneNumberDialog from "./PhoneNumberDialog";
    import {ClinicalCenterAdmin} from "../../utils/DrawerItems";
    import store from '../../store/index'
    import PasswordDialog from "./PasswordDialog";

    export default {
        name: "UserProfileView",
        components: {PasswordDialog, PhoneNumberDialog, AddressDialog, NameDialog, ProfileInfoItem},
        data: () => ({
            nameDialog: false,
            addressDialog: false,
            phoneNumberDialog: false,
            passwordDialog: false
        }),
        computed: {
            ...mapState('profile', ['profile']),
            avatarInitials() {
                if (!this.profile) return '';
                let firstName = this.profile.name;
                let firstNameInitial = !!firstName ? firstName[0].toUpperCase() : 'A';
                return `${firstNameInitial}`;
            },
            lastChanged() {
                if (!this.profile.lastPasswordResetDate) return 'a long time ago';
                let lastChange = new Date(this.profile.lastPasswordResetDate);
                let today = new Date();
                let difference = today - lastChange;
                let days = Math.round(difference / (1000 * 60 * 60 * 24));
                if (days === 1) return `${days} day ago`;
                if (days !== 0) return `${days} days ago`;
                let hours = Math.round(difference / (1000 * 60 * 60));
                if (hours === 1) return `${hours} hour ago`;
                if (hours !== 0) return `${hours} hours ago`;
                let minutes = Math.round(difference / (1000 * 60));
                if (minutes <= 1) return '1 minute ago';
                else return `${minutes} minutes ago`;
            },
            greetingMessages() {
                // da napravimo neku glupost od koda ovde, i ja malo da se poigram....
                return this.$store.state.auth.user.id === this.$route.id ? {
                    welcome: `Welcome, ${this.profile.name} ${this.profile.surname}`,
                    subtitle: `Manage your info and security to make eClinic work better for you.`
                } : {
                    welcome: `You're viewing the profile of user ${this.profile.name} ${this.profile.surname}`,
                    subtitle: `Don't do anything mean. After all, this is eClinic.`
                };
            }
        },
        created() {
            this.getProfileApi(this.$route.params.id);
        },
        methods: {
            ...mapActions('profile', ['getProfileApi']),
            ...mapActions('profile', ['updateProfileApi']),
            updateProfile(profile) {
                this.updateProfileApi(profile);
            },
            passwordChanged() {
                this.getProfileApi(this.$route.params.id);
            }

        },
        beforeRouteEnter(to, from, next) {
            let loggedUser = store.state.auth.user;
            let id = to.params.id;
            let role = store.state.auth.role;
            if (role !== ClinicalCenterAdmin.code && loggedUser.id !== id) {
                next('/')
            }
            next();
        },
    }
</script>

<style scoped>

</style>
