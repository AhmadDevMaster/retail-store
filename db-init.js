db = new Mongo().getDB("users");

db.createCollection('users', {capped: false});

db.users.insert([
  {
   	username: 'ahmad',
    password: '$2a$12$pf67lHftVqQLXDCvmSdfouSWfWk/ibdhp/I0zko749602.UEUeXeK',
    fullName: 'Ahmad Mohammad',
    roles: 'USER_ROLE',
    creationDate: new Date('2024-06-09T06:31:15.000Z')
  },
  {
    username: 'naji',
    password: '$2a$12$TJl6LCjRp.YEeEv5FNNigu.8YSrV7b9Y5hmILbzg6RLmB8zSZHA5m',
    fullName: 'Naji Mohammad',
    roles: 'USER_ROLE',
    creationDate: new Date('2024-06-09T06:31:15.000Z')
  },
]);

db = new Mongo().getDB("payments");
db.createCollection('discounts', {capped: false});

db.discounts.insert([
	{
		ruleType: 'Employee',
    	discountType: 'Percentage',
    	threshold: 0,
    	discountValue: 0.3
	},
	{
	    ruleType: 'Affiliate',
	    discountType: 'Percentage',
	    threshold: 0,
	    discountValue: 0.1
	},
	{
	    ruleType: 'Loyal',
	    discountType: 'Percentage',
	    threshold: 0,
	    discountValue: 0.05
	},
	{
	    ruleType: 'Threshold',
	    discountType: 'Fixed',
	    threshold: 100,
	    discountValue: 5
	}
])

