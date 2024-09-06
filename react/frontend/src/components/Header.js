import React from 'react'
import { AppBar, Toolbar, makeStyles, Typography } from "@material-ui/core";
import AcUnitRoundedIcon from "@material-ui/icons/AcUnitRounded";

const useStyles = makeStyles(() => ({
  typographyStyles: {
    flex: 1
  }
}));

const Header = () => {
	const classes = useStyles();
    return (
        <AppBar position="static">
            <Toolbar>
            	<Typography className={classes.typographyStyles}>
            		会社一覧
            	</Typography>
                <AcUnitRoundedIcon />
            </Toolbar>
        </AppBar>
    )
}

export default Header