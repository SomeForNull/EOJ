<template>
  <div id="userLoginView">
    <h2 style="margin-bottom: 16px">用户登录</h2>
    <a-form
      style="max-width: 480px; margin: 0 auto"
      label-align="left"
      auto-label-width
      :model="form"
      @submit="handleSubmit"
    >
      <a-form-item field="userAccount" label="账号">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item field="userPassword" tooltip="密码不少于 8 位" label="密码">
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item
        field="checkPassword"
        tooltip="确保两次密码一样"
        label="确认密码"
      >
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请再次输入密码"
        />
      </a-form-item>
      <a-form-item>
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
          "
        >
          <a-button type="primary" html-type="submit" style="width: 120px">
            注册
          </a-button>
          <a-button
            type="primary"
            html-type="button"
            @click="
              () => {
                router.push({ path: '/user/login' });
              }
            "
            style="width: 120px"
          >
            返回登录
          </a-button>
        </div>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from "vue";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { UserControllerService, UserRegisterRequest } from "../../../generated";
import { Message } from "@arco-design/web-vue";

/**
 * 表单信息
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as UserRegisterRequest);

const router = useRouter();

/**
 * 提交表单
 * @param data
 */
const handleSubmit = async () => {
  if (form.userPassword !== form.checkPassword) {
    Message.error("两次密码不同！");
    return;
  }
  const res = await UserControllerService.userRegisterUsingPost(form);
  // 注册成功，跳转到登录页
  if (res.code === 0) {
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("注册失败，" + res.message);
  }
};
</script>
