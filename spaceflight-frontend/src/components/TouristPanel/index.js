import React, {Component} from 'react';
import './style.css';
import axios from 'axios'

class TouristPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tourists: [],
            showTouristsList: true
        };

    }

    componentDidMount() {
        const endpoint = 'http://localhost:8080';
        axios.get(endpoint + '/tourists')
            .then((response) => {
                console.log(response.data);
                this.setState({
                    tourists: response.data
                });
            });
    }

    addButtonClick = () => {
        this.setState({
            ...this.state,
            showTouristsList: false
        });
    };

    render() {
        return (
            <div>
                {this.state.showTouristsList && this.createTouristView()}
                {!this.state.showTouristsList && this.createAddTouristView()}
                <div className="btn btn-primary"
                     onClick={this.addButtonClick}
                >
                    Add tourist
                </div>
            </div>
        )
    }

    createTouristView() {
        return <table className="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Gender</th>
                <th scope="col">Country</th>
                <th scope="col">Notes</th>
                <th scope="col">Date of birth</th>
            </tr>
            </thead>
            <tbody>{
                this.state.tourists.map((tourist, i) =>
                    (
                        <tr key={i}>
                            <th scope="row">{i}</th>
                            <td>{tourist.name}</td>
                            <td>{tourist.surname}</td>
                            <td>{tourist.gender}</td>
                            <td>{tourist.country}</td>
                            <td>{tourist.notes}</td>
                            <td>{tourist.dateOfBirth}</td>
                        </tr>
                    ))
            }
            </tbody>
        </table>;
    }

    createAddTouristView() {
        return (
            <div>
                <h3>Add User Panel</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input className="form-control" placeholder="Name" onChange={this.handleOnNameChange}/>
                    </div>
                    <div className="form-group">
                        <label>Surname</label>
                        <input className="form-control" placeholder="Surname" onChange={this.handleOnSurnameChange}/>
                    </div>
                    <div className="form-group">
                        <label>Gender</label>
                        <input className="form-control" placeholder="Gender" onChange={this.handleOnGenderChange}/>
                    </div>
                    <div className="form-group">
                        <label>Country</label>
                        <input className="form-control" placeholder="Country" onChange={this.handleOnCountryChange}/>
                    </div>
                    <div className="form-group">
                        <label>Notes</label>
                        <input className="form-control" placeholder="Notes" onChange={this.handleOnNotesChange}/>
                    </div>
                    <div className="form-group">
                        <label>Date of birth</label>
                        <input className="form-control" placeholder="Date of birth" onChange={this.handleOnBirthChange}/>
                    </div>

                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>

            </div>
        );
    }

    handleOnNameChange = (e) => {
        this.setState({
            ...this.state,
            name: e.target.value
        })
    }

    handleOnSurnameChange = (e) => {
        this.setState({
            ...this.state,
            surname: e.target.value

        })
    }
    handleOnCountryChange = (e) => {
        this.setState({
            ...this.state,
            country: e.target.value
        })
    }
    handleOnGenderChange = (e) => {
        this.setState({
            ...this.state,
            gender: e.target.value
        })
    }

    handleOnNotesChange = (e) => {
        this.setState({
            ...this.state,
            notes: e.target.value
        })
    }

    handleOnBirthChange = (e) => {
        this.setState({
            ...this.state,
            dateOfBirth: e.target.value

        })
    }

    onSubmit = (e) => {
        axios.post('http://localhost:8080/tourists',
            {
                "name": this.state.name,
                "surname": this.state.surname,
                "gender": this.state.gender,
                "country": this.state.country,
                "notes": this.state.notes,
                "dateOfBirth": this.state.dateOfBirth,
                "flightEntities": []
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}

export default TouristPanel;
