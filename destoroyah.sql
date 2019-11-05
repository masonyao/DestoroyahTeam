
select * from users;
select * from post;
select * from photo;

--passwor hashing
create or replace function hashPassword()
returns trigger 
as $$
	begin	
		if(new.userpass=old.userpass)then
			return new;
		end if;
	new.userpass :=
	md5(new.email||new.userpass||'steak');
return new;
	end;
$$ language plpgsql;

drop trigger hashPass;

create trigger hashPass
before insert or update on users
for each row
execute function hashPassword();

drop function hash_dat(text,text);

create or replace function hash_dat(z text, y text)
returns table("email" varchar(255))		
as $$
begin 
	return query
	select username from users where userpass = md5(z||y||'steak');
end;$$
language plpgsql;

select hash_dat('willdawg@mail.com','peach');

	select * from users where userpass = md5('willdawg@mail.com'||'peach'|| 'steak');

select * from users where username = 'willdawg';



