create table bicycle (
    id serial primary key,
    category_id integer references category(id) on delete set null,
    "name" text not null,
    thumbnail text,
    images text[],
    information json,
    suitable_user json,
    transmission_system json,
    frame json,
    wheelset json,
    created_at timestamp with time zone DEFAULT (now()),
    created_by int,
    updated_at timestamp with time zone,
    updated_by int,
    deleted boolean DEFAULT false
);

insert into public.category (id, name, description, created_by, updated_by)
values  (22, 'MOUNTAIN BIKES', 'MOUNTAIN BIKES', 1, 1),
        (23, 'ROAD BIKES', 'ROAD BIKES', 1, 1),
        (24, 'TOURING BIKES', 'TOURING BIKES', 1, 1),
        (25, 'CITY BIKES', 'CITY BIKES', 1, 1),
        (26, 'KID BIKES', 'KID BIKES', 1, 1);

insert into public.bicycle (category_id, name, thumbnail, images, information, suitable_user, transmission_system, frame, wheelset) values
  (22, 'Cannondale Trail 6 Bike', null, null, '{
    "paintMaterial": "Acrylic",
    "handlebarStemMaterial": "Aluminum alloy",
    "handlebar": "Straight handlebar",
    "saddleMaterial": "Leather",
    "seatpost": "Adjustable seatpost",
    "seatpostMaterial": "Carbon fiber",
    "steel": "Stainless steel",
    "bicycleSaddleBrand": "Brooks",
    "features": "Disc brakes, fenders, kickstand",
    "brand": "Velocity"
  }', '{
    "recommendedAge": "18+",
    "recommendedHeight": "5''6\" - 6''2\"",
    "bikeWeightLimit": "220 lbs",
    "pillionWeightLimit": "N/A",
    "sizeWeight": "Medium (54cm), 22.5 lbs"
  }', ' {
    "shiftLever": "Shimano Altus",
    "shiftLeverType": "Rapidfire Plus",
    "crankset": "Shimano",
    "brakeSystem": "Hydraulic disc brake",
    "braleLever": "Shimano",
    "brakeType": "Flat mount",
    "cassette": "Shimano Deore",
    "chain": "KMC X10",
    "chainring": "FSA"
  }', '{
    "frame": "Aluminum alloy",
    "suspension": "Front suspension"
  }', '{
    "wheelSize": "700c",
    "rim": "Double wall alloy rim",
    "hub": "Novatec",
    "spoke": "Stainless steel",
    "tire": "Schwalbe Marathon Plus",
    "valveType": "Presta",
    "brand": "Mavic"
  }'),
  (23, 'Bike 8', null, null, '{
    "paintMaterial": "Enamel",
    "handlebarStemMaterial": "Carbon fiber",
    "handlebar": "Drop bar",
    "saddleMaterial": "Synthetic leather",
    "seatpost": "Fixed seatpost",
    "seatpostMaterial": "Aluminum alloy",
    "steel": "Chromoly steel",
    "bicycleSaddleBrand": "Selle Royal",
    "features": "Rear rack, mudguards, bell",
    "brand": "Giant"
  }', '{
    "recommendedAge": "18+",
    "recommendedHeight": "5''8\" - 6''4\"",
    "bikeWeightLimit": "250 lbs",
    "pillionWeightLimit": "N/A",
    "sizeWeight": "Large (58cm), 23.2 lbs"
  }',' {
    "shiftLever": "Shimano Sora",
    "shiftLeverType": "STI",
    "crankset": "Shimano Tiagra",
    "brakeSystem": "Mechanical disc brake",
    "braleLever": "Shimano",
    "brakeType": "Post mount",
    "cassette": "Shimano HG50",
    "chain": "SRAM PC-1031",
    "chainring": "Shimano"
  }', ' {
    "frame": "Carbon fiber",
    "suspension": "No suspension"
  }', ' {
    "wheelSize": "700c",
    "rim": "Aluminum rim",
    "hub": "Shimano",
    "spoke": "Stainless steel",
    "tire": "Continental Gatorskin",
    "valveType": "Presta",
    "brand": "DT Swiss"
  }'),
  (24, 'Trail 9 Bike', null, null, '{
    "paintMaterial": "Powder coat",
    "handlebarStemMaterial": "Aluminum alloy",
    "handlebar": "Riser bar",
    "saddleMaterial": "Gel foam",
    "seatpost": "Adjustable seatpost",
    "seatpostMaterial": "Aluminum alloy",
    "steel": "High-tensile steel",
    "bicycleSaddleBrand": "Serfas",
    "features": "Front suspension, kickstand, reflectors",
    "brand": "Trek"
  }', ' {
    "recommendedAge": "12+",
    "recommendedHeight": "5''2\" - 5''10\"",
    "bikeWeightLimit": "200 lbs",
    "pillionWeightLimit": "100 lbs",
    "sizeWeight": "Small (48cm), 21.5 lbs"
  }', ' {
    "shiftLever": "Shimano Tourney",
    "shiftLeverType": "Trigger shifter",
    "crankset": "Shimano",
    "brakeSystem": "V-brake",
    "braleLever": "Shimano",
    "brakeType": "Linear pull",
    "cassette": "Shimano HG200",
    "chain": "KMC Z7",
    "chainring": "ProWheel"
  }', '{
    "frame": "Aluminum alloy",
    "suspension": "Front suspension"
  }', '{
    "wheelSize": "27.5",
    "rim": "Alloy rim",
    "hub": "Formula",
    "spoke": "Stainless steel",
    "tire": "Bontrager H2",
    "valveType": "Schrader",
    "brand": "Bontrager"
  }'),
  (25, 'Bike 9', null, null, ' {
    "paintMaterial": "Matte finish",
    "handlebarStemMaterial": "Carbon fiber",
    "handlebar": "Flat bar",
    "saddleMaterial": "Leather",
    "seatpost": "Adjustable seatpost",
    "seatpostMaterial": "Carbon fiber",
    "steel": "Stainless steel",
    "bicycleSaddleBrand": "Brooks",
    "features": "Internal cable routing, disc brakes",
    "brand": "Specialized"
  }', '{
    "recommendedAge": "16+",
    "recommendedHeight": "5''2\" - 5''10\"",
    "bikeWeightLimit": "220 lbs",
    "pillionWeightLimit": "N/A",
    "sizeWeight": "Medium (54cm), 22.8 lbs"
}', ' {
    "shiftLever": "Shimano Deore",
    "shiftLeverType": "Rapidfire Plus",
    "crankset": "Shimano Deore",
    "brakeSystem": "Hydraulic disc brake",
    "braleLever": "Shimano",
    "brakeType": "Flat mount",
    "cassette": "Shimano Deore",
    "chain": "Shimano HG54",
    "chainring": "Shimano"
  }', '{
    "frame": "Carbon fiber",
    "suspension": "No suspension"
  }', '{
    "wheelSize": "29\"",
    "rim": "Carbon fiber rim",
    "hub": "Shimano",
    "spoke": "Sapim CX-Ray",
    "tire": "Specialized Fast Trak",
    "valveType": "Presta",
    "brand": "Roval"
  }'),
  (25, 'Bike 10', null, null, ' {
    "paintMaterial": "Metallic finish",
    "handlebarStemMaterial": "Aluminum alloy",
    "handlebar": "Flat bar",
    "saddleMaterial": "Gel foam",
    "seatpost": "Fixed seatpost",
    "seatpostMaterial": "Aluminum alloy",
    "steel": "Chromoly steel",
    "bicycleSaddleBrand": "Bontrager",
    "features": "Front suspension, kickstand",
    "brand": "Cannondale"
  }', '{
    "recommendedAge": "14+",
    "recommendedHeight": "5''4\"-5''10\"",
    "bikeWeightLimit": "250 lbs",
    "pillionWeightLimit": "N/A",
    "sizeWeight": "Medium (52cm), 24.1 lbs"
  }', '{
    "shiftLever": "Shimano Altus",
    "shiftLeverType": "Rapidfire",
    "crankset": "Prowheel",
    "brakeSystem": "Mechanical disc brake",
    "braleLever": "Shimano",
    "brakeType": "Post mount",
    "cassette": "Shimano HG31",
    "chain": "KMC Z8.3",
    "chainring":  "Prowheel"
  }', '{
    "frame": "Aluminum alloy",
    "suspension": "SR Suntour XCT"
  }', '{
    "wheelSize": "27.5\"",
    "rim": "Double wall alloy rim",
    "hub": "Formula",
    "spoke": "Stainless steel",
    "tire": "Kenda K1153",
    "valveType": "Schrader",
    "brand": "Cannondale"
  }')
