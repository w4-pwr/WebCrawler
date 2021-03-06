import React from 'react'

export default React.createClass({            
    render() {
        return <section className='content-header'>
            <form action='#' method='get' className=''>
                <div className='input-group form-group form-group-lg'>
                  <input type='text' name='q' className='form-control' placeholder='Add new search...' style={{backgroundImage: 'none', backgroundPosition: '0% 0%', backgroundRepeat: 'repeat'}} />
                  <span className='input-group-btn'>
                    <button type='submit' name='search' onClick={this.addSearch}id='search-btn' className='btn btn-lg'><i className='fa fa-plus' />
                    </button>
                  </span>
                </div>
            </form>
        </section>
    },
    addSearch(e) {
        $.post(backendUrl + 'serach?token=' + localStorage.getItem('token'),{
            keyword: $(e.currentTarget).closest('div').find('input')[0].value
        });
    }
});
