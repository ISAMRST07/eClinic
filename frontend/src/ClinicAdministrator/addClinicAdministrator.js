import React from 'react';
import axios from 'axios';
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Divider from "@material-ui/core/Divider";
import Button from "@material-ui/core/Button";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";

class AddClinicAdministrator extends React.Component {
    constructor(props) {
        super(props);
        this.city = "";
    };

    handleChange(event) {
        this.city = event.target.value.trim();


    };


    addClinicAdministrator = (event) => {
        console.log("Kliknuo");
       // axios.post()
    };

    render() {
        return (
            <div>
                <Typography component="h1" variant="h4" align="center">
                    Add a new clinic administrator
                </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <TextField
                        required
                        id="name"
                        name="name"
                        label="Name"
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        id="username"
                        name="username"
                        label="Username"
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        id="password"
                        name="password"
                        label="Password"
                        fullWidth
                    />
                </Grid>
                <Divider light />
                <Button align-items="center" variant="contained" color="primary" type="submit" onClick={this.addClinicAdministrator}>
                    Submit
                </Button>
            </Grid>
            </div>

        );
    }
}

export default AddClinicAdministrator;