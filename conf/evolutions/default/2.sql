# --- !Ups

create table account (
  id                        bigint auto_increment not null,
  amount                    double,
  user_id                   bigint,
  constraint pk_account primary key (id))
;

alter table account add constraint fk_account_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_account_user_3 on account (user_id);

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

SET FOREIGN_KEY_CHECKS=1;