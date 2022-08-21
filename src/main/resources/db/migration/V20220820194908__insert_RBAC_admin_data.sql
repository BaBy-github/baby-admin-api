INSERT INTO `tb_action` (`id`, `code`) VALUES (1, 'INSERT');
INSERT INTO `tb_action` (`id`, `code`) VALUES (2, 'DELETE');
INSERT INTO `tb_action` (`id`, `code`) VALUES (3, 'UPDATE');
INSERT INTO `tb_action` (`id`, `code`) VALUES (4, 'SELECT');

INSERT INTO `tb_model` (`id`, `code`) VALUES (1, 'USER');

INSERT INTO `tb_permission` (`id`, `code`, `action_id`, `model_id`) VALUES (1, 'USER:INSERT', 1, 1);
INSERT INTO `tb_permission` (`id`, `code`, `action_id`, `model_id`) VALUES (2, 'USER:DELETE', 2, 1);
INSERT INTO `tb_permission` (`id`, `code`, `action_id`, `model_id`) VALUES (3, 'USER:UPDATE', 3, 1);
INSERT INTO `tb_permission` (`id`, `code`, `action_id`, `model_id`) VALUES (4, 'USER:SELECT', 4, 1);

INSERT INTO `tb_role` (`id`, `role_name`) VALUES (1, 'admin');

INSERT INTO `tb_user` (`id`, `username`, `password`) VALUES (1, 'admin', 'admin');

INSERT INTO `m_role_permission` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `m_role_permission` (`role_id`, `permission_id`) VALUES (1, 2);
INSERT INTO `m_role_permission` (`role_id`, `permission_id`) VALUES (1, 3);
INSERT INTO `m_role_permission` (`role_id`, `permission_id`) VALUES (1, 4);

INSERT INTO `m_user_role` (`user_id`, `role_id`) VALUES (1, 1);
