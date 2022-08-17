# Response
  Current contest as an example. Feel free to edit/remove it.

## Required
### API Document
  Import [this](#api-document) json file to Postman

* List all pharmacies open at a specific time and on a day of the week if requested.
	
	Url : /getOpenPharmacies
	Mehtod : GET
	Parameters : 
		day : (required) enum -> ("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun") 
		openTime : (not required) int -> 0 ~ 23
	Response :
	[
		{
        "name": "Welltrack",
        "cashBalance": 507.29,
        "openingHours": "Mon - Fri 08:00 - 17:00 / Sat, Sun 08:00 - 12:00",
        "masks": [
            {
                "name": "Second Smile (blue) (6 per pack)",
                "price": 8.66
            },
            {
                "name": "True Barrier (green) (10 per pack)",
                "price": 20.58
            },
            {
                "name": "Masquerade (blue) (6 per pack)",
                "price": 21.87
            },
            {
                "name": "MaskT (green) (10 per pack)",
                "price": 48.76
            },
            {
                "name": "Cotton Kiss (black) (10 per pack)",
                "price": 16.31
            },
            {
                "name": "Masquerade (blue) (10 per pack)",
                "price": 24.89
            }
        ]
    	}
		.......
	]
* List all masks sold by a given pharmacy, sorted by mask name or price.
	Url : /getPharmaciesSold
	Mehtod : GET
	Parameters : 
		pharmacyName : (required) String 
		sortBy : (required) String -> (name || price )
	Response :
	[
		{
			"name": "MaskT (green) (10 per pack)",
			"price": 41.86
		},
		{
			"name": "Masquerade (green) (3 per pack)",
			"price": 9.4
		},
		{
			"name": "Second Smile (black) (10 per pack)",
			"price": 31.98
		},
		{
			"name": "Second Smile (black) (3 per pack)",
			"price": 5.84
		},
		{
			"name": "True Barrier (green) (3 per pack)",
			"price": 13.7
		}
		.....
	]	
* List all pharmacies with more or less than x mask products within a price range.
	Url : /getPharmaciesSearch
	Mehtod : GET
	Parameters : 
		minPrice : (required) int
		maxPrice : (required) int
		search : (required) [greater|less]:[int]
	Response :
	[
		    {
				"name": "First Care Rx",
				"cashBalance": 222.52,
				"openingHours": "Mon - Fri 08:00 - 17:00",
				"masks": [
					{
						"name": "Second Smile (blue) (10 per pack)",
						"price": 49.83
					},
					{
						"name": "Second Smile (green) (6 per pack)",
						"price": 27.69
					},
					{
						"name": "Second Smile (blue) (6 per pack)",
						"price": 11.07
					},
					{
						"name": "Masquerade (black) (10 per pack)",
						"price": 19.95
					},
					{
						"name": "Masquerade (green) (10 per pack)",
						"price": 37.3
					},
					{
						"name": "Masquerade (black) (3 per pack)",
						"price": 12.0
					},
					{
						"name": "MaskT (black) (10 per pack)",
						"price": 44.4
					},
					{
						"name": "MaskT (green) (10 per pack)",
						"price": 37.87
					},
					{
						"name": "Second Smile (black) (3 per pack)",
						"price": 14.96
					}
				]
			}
		.......
	]
* The top x users by total transaction amount of masks within a date range.
	Url : /getTopUsers
	Mehtod : GET
	Parameters : 
		from : (required) String (Date) : ex 2021-02-02 00:00:00 
		to :   (required) String (Date) : ex 2021-02-20 00:00:00 
		top :  (required) int top N user
	Response :
	[
		{
			"name": "Wilbert Love",
			"cashBalance": 796,
			"purchaseHistories": [
				{
					"pharmacyName": "Foundation Care",
					"maskName": "Masquerade (green) (10 per pack)",
					"transactionDate": "2021-01-02 02:38:09",
					"transactionAmount": 29.87
				},
				{
					"pharmacyName": "First Care Rx",
					"maskName": "Second Smile (black) (3 per pack)",
					"transactionDate": "2021-01-03 07:30:30",
					"transactionAmount": 14.24
				},
				{
					"pharmacyName": "Carepoint",
					"maskName": "Masquerade (blue) (6 per pack)",
					"transactionDate": "2021-01-07 05:07:21",
					"transactionAmount": 6.44
				},
				{
					"pharmacyName": "RX Universal",
					"maskName": "True Barrier (blue) (3 per pack)",
					"transactionDate": "2021-01-07 15:18:41",
					"transactionAmount": 7.16
				},
				{
					"pharmacyName": "Keystone Pharmacy",
					"maskName": "True Barrier (green) (3 per pack)",
					"transactionDate": "2021-01-13 00:19:32",
					"transactionAmount": 11.3
				},
				{
					"pharmacyName": "First Pharmacy",
					"maskName": "Cotton Kiss (green) (10 per pack)",
					"transactionDate": "2021-01-13 01:18:23",
					"transactionAmount": 17.5
				},
				{
					"pharmacyName": "Medlife",
					"maskName": "True Barrier (green) (10 per pack)",
					"transactionDate": "2021-01-17 19:50:27",
					"transactionAmount": 36.25
				},
				{
					"pharmacyName": "Prescription Hope",
					"maskName": "Cotton Kiss (black) (3 per pack)",
					"transactionDate": "2021-01-18 02:47:27",
					"transactionAmount": 5.61
				},
				{
					"pharmacyName": "RX Universal",
					"maskName": "True Barrier (blue) (3 per pack)",
					"transactionDate": "2021-01-28 23:17:21",
					"transactionAmount": 6.79
				}
			]
		}
		.......
	]
* The total number of masks and dollar value of transactions within a date range.
	Url : /searcHistory
	Mehtod : GET
	Parameters : 
		from : (required) String (Date) : ex 2021-02-02 00:00:00 
		to :   (required) String (Date) : ex 2021-02-20 00:00:00 
	Response :
[
    {
        "name": "True Barrier (blue) (3 per pack)",
        "number": 1,
        "totalPrice": 7
    },
    {
        "name": "Cotton Kiss (blue) (10 per pack)",
        "number": 2,
        "totalPrice": 24
    }
	.......
] 
* Search for pharmacies or masks by name, ranked by relevance to the search term.
	Url : /searchPharmacies
	Mehtod : GET
	Parameters : 
		searchInfo : (required) String 
		searchBy :   (required) String (pharamcies || mask )
	Response :
	show after buy user history 
	[
    {
        "name": "Yvonne Guerrero",
        "cashBalance": 191,
        "purchaseHistories": [
            {
                "pharmacyName": "Keystone Pharmacy",
                "maskName": "True Barrier (green) (3 per pack)",
                "transactionDate": "2021-01-04 15:18:51",
                "transactionAmount": 12.35
            },
            {
                "pharmacyName": "Medlife",
                "maskName": "True Barrier (green) (10 per pack)",
                "transactionDate": "2021-01-17 05:41:10",
                "transactionAmount": 38.43
            },
            {
                "pharmacyName": "RX Universal",
                "maskName": "True Barrier (blue) (3 per pack)",
                "transactionDate": "2021-01-20 12:23:09",
                "transactionAmount": 6.99
            },
            {
                "pharmacyName": "Keystone Pharmacy",
                "maskName": "Second Smile (blue) (6 per pack)",
                "transactionDate": "2021-01-20 13:20:43",
                "transactionAmount": 14.52
            },
            {
                "pharmacyName": "Welltrack",
                "maskName": "True Barrier (green) (10 per pack)",
                "transactionDate": "2021-01-26 20:37:13",
                "transactionAmount": 20.91
            },
            {
                "pharmacyName": "Prescription Hope",
                "maskName": "Cotton Kiss (green) (10 per pack)",
                "transactionDate": "2021-01-30 18:58:57",
                "transactionAmount": 42.63
            },
            {
                "pharmacyName": "Keystone Pharmacy",
                "maskName": "True Barrier (green) (3 per pack)",
                "transactionDate": "2022-08-14 06:55:48",
                "transactionAmount": 12.06
            }
        ]
    }	
		.......
	]
* Process a user purchases a mask from a pharmacy, and handle all relevant data changes in an atomic transaction.
	Url : /buyMask
	Mehtod : POST
	Body : 
	{
		"userName":"Yvonne Guerrero",
		"pharamacy":"Keystone Pharmacy",
		"maskName":"True Barrier (green) (3 per pack)"
	}
	Response :
	show after buy user history 
	[
    {
        "name": "Yvonne Guerrero",
        "cashBalance": 191,
        "purchaseHistories": [
            {
                "pharmacyName": "Keystone Pharmacy",
                "maskName": "True Barrier (green) (3 per pack)",
                "transactionDate": "2021-01-04 15:18:51",
                "transactionAmount": 12.35
            },
            {
                "pharmacyName": "Medlife",
                "maskName": "True Barrier (green) (10 per pack)",
                "transactionDate": "2021-01-17 05:41:10",
                "transactionAmount": 38.43
            },
            {
                "pharmacyName": "RX Universal",
                "maskName": "True Barrier (blue) (3 per pack)",
                "transactionDate": "2021-01-20 12:23:09",
                "transactionAmount": 6.99
            },
            {
                "pharmacyName": "Keystone Pharmacy",
                "maskName": "Second Smile (blue) (6 per pack)",
                "transactionDate": "2021-01-20 13:20:43",
                "transactionAmount": 14.52
            },
            {
                "pharmacyName": "Welltrack",
                "maskName": "True Barrier (green) (10 per pack)",
                "transactionDate": "2021-01-26 20:37:13",
                "transactionAmount": 20.91
            },
            {
                "pharmacyName": "Prescription Hope",
                "maskName": "Cotton Kiss (green) (10 per pack)",
                "transactionDate": "2021-01-30 18:58:57",
                "transactionAmount": 42.63
            },
            {
                "pharmacyName": "Keystone Pharmacy",
                "maskName": "True Barrier (green) (3 per pack)",
                "transactionDate": "2022-08-14 06:55:48",
                "transactionAmount": 12.06
            }
        ]
    }	
		.......
	]
 
* show all Pharmacies

	Url : /showPharmacies
	Mehtod : GET

* show user

	Url : /showPharmacies
	Mehtod : GET
	Parameter : user = xxx (default show all users )

	
### Import Data Commands
  `rake import_data:book_store[PATH_TO_FILE]`  
  `rake import_data:user[PATH_TO_FILE]`

## Bonus
### Test Coverage Report
  check report [here](#test-coverage-report)

### Dockerized
  check my dockerfile [here](#dockerized)

### Demo Site Url
  demo site is ready on [heroku](#demo-site-url)
domian name : https://quickstart-image-7rjbxoowca-uc.a.run.app/