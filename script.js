document.getElementById('checkAvailabilityBtn').addEventListener('click', function() {
    var location = document.getElementById('location').value;
    var manufacturer = document.getElementById('manufacturer').value;
    var dealer = document.getElementById('dealer').value;
    
    var availabilityResults = document.getElementById('availabilityResults');
    
    var availabilityData = [
        { product: 'iPhone', location: 'Goa', manufacturer: 'Apple', dealer: 'XYZ Motors' },
        { product: 'Galaxy S21', location: 'Chennai', manufacturer: 'Samsung', dealer: 'ABC Autos' },
        { product: 'Bravia TV', location: 'Delhi', manufacturer: 'Sony', dealer: '123 Car Dealership' },
        { product: 'Surface Laptop', location: 'Goa', manufacturer: 'Microsoft', dealer: 'XYZ Motors' },
        { product: 'Corolla', location: 'Goa', manufacturer: 'Toyota', dealer: 'XYZ Motors' },
        { product: 'iPad', location: 'Chennai', manufacturer: 'Apple', dealer: 'ABC Autos' },
        { product: 'Xperia', location: 'Delhi', manufacturer: 'Sony', dealer: '123 Car Dealership' },
        { product: 'Camry', location: 'Delhi', manufacturer: 'Toyota', dealer: '123 Car Dealership' }
    ];

    // Filtering data
    var filteredData = availabilityData.filter(function(item) {
        return (location === 'all' || item.location === location) &&
               (manufacturer === 'all' || item.manufacturer === manufacturer) &&
               (dealer === 'all' || item.dealer === dealer);
    });

    // Display the filtered availability data
    if (filteredData.length > 0) {
        var resultHTML = '<ul>';
        filteredData.forEach(function(item) {
            resultHTML += `<li>${item.product} - ${item.location}, ${item.manufacturer}, ${item.dealer}</li>`;
        });
        resultHTML += '</ul>';
        availabilityResults.innerHTML = resultHTML;
    } 
    
    
    
    
    else {
        availabilityResults.innerHTML = '<p>No results found.</p>';
    }
});
