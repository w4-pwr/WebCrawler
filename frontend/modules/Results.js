import React from 'react'
import BigSearch from './BigSearch'
import Pagination from './Pagination'

export default React.createClass({
    componentWillMount() {
        this.data = {
            search: {
                id: 'xxxxxx',
                keyword: 'Lech Wałęsa',
                addingDate: 1463496202011,
                howManyResults: 15,
                crawlingTime: 3.22
            },
            results: [
                {
                    id: 'xxxxxx',
                    url: 'http:/google.com',
                    repeats: 12
                },{
                    id: 'xxxx23',
                    url: 'http:/facebook.com',
                    repeats: 23
                }
            ],
            pages: 30
        };
        this.getResults();
    },
    getResults() {
        $.get(backendUrl + '/result?token=' + localStorage.getItem('token') +
            '&id=' + this.props.params.id + '&page=' + this.props.params.page, function(data){
            this.data = data;
            this.forceUpdate();
        },'json');
    },
    render() {
        if (!this.props.params.page) {
            this.props.params.page = 1;
        }
        return <div className='content-wrapper'>
            <BigSearch />

            {/* Main content */}
            <section className='container container-fluid'>
                <div className='row'>
                    <div className='col-md-12'><b>{this.data.search.keyword}</b></div>
                    <div className='col-md-12'>{this.data.search.howManyResults} results found in {this.data.search.crawlingTime} seconds, {moment(this.data.search.addingDate).fromNow()}</div>
                </div>
                {this.renderRows()}
                <Pagination url={'#/search/'+this.props.params.id+'/'} page={this.props.params.page} pages={this.data.pages} />
            </section>
        </div>;
    },
    renderRows() {
        return this.data.results.map((el)=>{
            return <div className='row'>
                <div className='col-md-12'><b><u><a href={el.url}>{el.url}</a></u></b>, {el.repeats} matches</div>
            </div>;
        })
    }
});
