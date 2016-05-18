import React from 'react'
import BigSearch from './BigSearch'
import Pagination from './Pagination'

export default React.createClass({
    componentWillMount() {
        if (!this.props.params.page) {
            this.props.params.page = 1;
        }
        this.data = {
            searches: [
                {
                    id: 'xxxxxx',
                    keyword: 'Lech Wałęsa',
                    addingDate: 1463496202011,
                    howManyResults: 15,
                    crawlingTime: 3.22
                },{
                    id: 'xxxx23',
                    keyword: 'Jan Kowalski',
                    addingDate: 1463496202011,
                    howManyResults: 15,
                    crawlingTime: 1.334
                },{
                    id: 'xxx23423xx',
                    keyword: 'Juwenalia 2017',
                    addingDate: 1463496202011,
                    howManyResults: 15,
                    crawlingTime: 0.012
                }
            ],
            pages: 30
        };
        this.getSearchList();
    },
    getSearchList() {
        $.get(backendUrl + 'search?token=' + localStorage.getItem('token') + '&page=' + this.props.params.page, function(data){
            this.data = data;
            this.forceUpdate();
        },'json');
    },
    render() {
        return <div className='content-wrapper'>
            <BigSearch />

            {/* Main content */}
            <section className='container container-fluid'>
                {this.renderRows()}
                <Pagination url='#/s/' page={this.props.params.page} pages={this.data.pages} />
            </section>
        </div>;
    },
    renderRows() {
        return this.data.searches.map((el)=>{
            return <div className='row'>
                <div className='col-md-12'><b><u><a href={'#/search/'+el.id}>{el.keyword}</a></u></b></div>
                <div className='col-md-12'>{el.howManyResults} results found in {el.crawlingTime} seconds, {moment(el.addingDate).fromNow()}</div>
            </div>;
        })
    }
});
