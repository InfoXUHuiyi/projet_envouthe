1/dataset
NYC Property Sales: A year's worth of properties sold on the NYC real estate market

2/target and features
The nyc-rolling-sales.csv.zip (2 Mb) is a record of every building or building unit (apartment, etc.) 
sold in the New York City property market over a 12-month period from September 2016 to September 2017. 

I chose 'Borough','Block' and 'Zip code' as the features to create the linear regression model.
And I chose 'Sale price' as the target.

3/dataset features
The dataset contains the following features:
Borough: (N) 
The name of the borough in which the property is located. A digit code for the borough the property is located in; 
in order these are Manhattan (1), Bronx (2), Brooklyn (3), Queens (4), and Staten Island (5).

Neighborhood: (S) 
Department of Finance assessors determine the neighborhood name in the course of valuing properties. 
The common name of the neighborhood is generally the same as the name Finance designates. 
However, there may be slight differences in neighborhood boundary lines and some sub-neighborhoods may not be included.

Building Class Category: (S) 
This is a field that we are including so that users of the Rolling Sales Files can easily identify similar properties by broad usage 
(e.g. One Family Homes) without looking up individual Building Classes. 
Files are sorted by Borough, Neighborhood, Building Class Category, Block and Lot.

Tax Class at Present: (N) 
Every property in the city is assigned to one of four tax classes (Classes 1, 2, 3, and 4), based on the use of the property.
Class 1: Includes most residential property of up to three units 
(such as one-, two-, and three-family homes and small stores or offices with one or two attached apartments), 
vacant land that is zoned for residential use, and most condominiums that are not more than three stories.
Class 2: Includes all other property that is primarily residential, such as cooperatives and condominiums.
Class 3: Includes property with equipment owned by a gas, telephone or electric company.
Class 4: Includes all other properties not included in class 1,2, and 3, such as offices, factories, warehouses, garage buildings, etc.

Block: (N) 
A Tax Block is a sub-division of the borough on which real properties are located. 
The Department of Finance uses a Borough-Block-Lot classification to label all real property in the City. 
“Whereas” addresses describe the street location of a property, the block and lot distinguishes one unit of real property from another, 
such as the different condominiums in a single building. 
Also, block and lots are not subject to name changes based on which side of the parcel the building puts its entrance on.

Lot: (N) 
A Tax Lot is a subdivision of a Tax Block and represents the property unique location. 
The combination of borough, block, and lot forms a unique key for property in New York City. 
Commonly called a BBL.

Easement: (S) 
An easement is a right, such as a right of way, which allows an entity to make limited use of another’s real property. 
For example: MTA railroad tracks that run across a portion of another property.

Building Class at Present: (S) 
The Building Classification is used to describe a property’s constructive use. 
The first position of the Building Class is a letter that is used to describe a general class of properties 
(for example “A” signifies one-family homes, “O” signifies office buildings. “R” signifies condominiums). 
The second position, a number, adds more specific information about the property’s use or construction style 
(using our previous examples “A0” is a Cape Cod style one family home, “O4” is a tower type office building and “R5” is a commercial condominium unit). 
The term Building Class used by the Department of Finance is interchangeable with the term Building Code used by the Department of Buildings.

Address: (S) 
The street address of the property as listed on the Sales File. Coop sales include the apartment number in the address field.

Apartement Number (S)

Zip Code: (N) 
The property’s postal code

Residential Units: (N) 
The number of residential units at the listed property.

Commercial Units: (N) 
The number of commercial units at the listed property.

Total Units: (N) 
The total number of units at the listed property.

Land Square Feet: (N) 
The land area of the property listed in square feet.

Gross Square Feet: (N) 
The total area of all the floors of a building as measured from the exterior surfaces of the outside walls of the building, including the land area and space within any building or structure on the property.

Year Built: (N) 
Year the structure on the property was built.

Tax Class at Time of Sale (N)

Building Class at Time of Sale: (S)

Sales Price: (N) 
Price paid for the property. A  0  sale indicates that there was a transfer of ownership without a cash consideration. 
There can be a number of reasons for a  0  sale including transfers of ownership from parents to children.

Sale Date: (D) 
Date the property sold.

Note: (N): number, (S): string, (D): date

