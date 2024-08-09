package com.yunshu.easyoj.judge.codesandbox;

import com.yunshu.easyoj.judge.codesandbox.impl.ExampleCodeSandboxImpl;
import com.yunshu.easyoj.judge.codesandbox.impl.RemoteCodeSandboxImpl;
import com.yunshu.easyoj.judge.codesandbox.impl.ThirdPartyCodeSandboxImpl;

/**
 * 代码沙箱工厂
 */
public class CodeSandBoxFactory {
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandboxImpl();
            case "remote":
                return new RemoteCodeSandboxImpl();
            case "ThirdParty":
                return new ThirdPartyCodeSandboxImpl();
            default:
                return new ExampleCodeSandboxImpl();
        }
    }
}
