import React from 'react'

export default React.createClass({
    componentWillMount() {
        this.data = {user: {}};   
    },
    render() {
        return <div className="content-wrapper">
            <section className="content-header">
                <h3>Your profile</h3>
            </section>

            {/* Main content */}
            <section className="content">
                <div>{this.data.user.firstName} {this.data.user.lastName}, {this.data.user.username}</div>
                <div>{this.data.user.email}</div>
                {(()=>{
                    if (this.data.user.role == 'ADMIN') {
                        return <div>ADMINISTRATOR</div>                                
                    } else {
                        return null;
                    }
                })()}
            </section>
        </div>;
    },
    getUserData() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                this.data.user = JSON.parse(xhttp.responseText);
                this.forceUpdate();
            }
        };
        xhttp.open('GET', this.backendUrl + '/user/'+localStorage.getItem('token') , true);
        xhttp.send();
    },
});
