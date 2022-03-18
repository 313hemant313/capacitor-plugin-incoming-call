# capacitor-plugin-incoming-call

capacitor-plugin-incoming-call

## Install

```bash
npm install capacitor-plugin-incoming-call
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`detectCallState(...)`](#detectcallstate)
* [`addListener('callStateChange', ...)`](#addlistenercallstatechange)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### detectCallState(...)

```typescript
detectCallState(options: { action: string; }) => Promise<{ action: string; }>
```

To enable / disable detection of calls 
options: { action: 'ACTIVATE' | 'DEACTIVATE' }

| Param         | Type                             |
| ------------- | -------------------------------- |
| **`options`** | <code>{ action: string; }</code> |

**Returns:** <code>Promise&lt;{ action: string; }&gt;</code>

--------------------


### addListener('callStateChange', ...)

```typescript
addListener(eventName: 'callStateChange', listenerFunc: CallStateChangeListener) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                        |
| ------------------ | --------------------------------------------------------------------------- |
| **`eventName`**    | <code>'callStateChange'</code>                                              |
| **`listenerFunc`** | <code><a href="#callstatechangelistener">CallStateChangeListener</a></code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### PhoneState

| Prop             | Type                                                      | Description                                                                   | Since |
| ---------------- | --------------------------------------------------------- | ----------------------------------------------------------------------------- | ----- |
| **`callActive`** | <code>boolean</code>                                      | Whether there is an active call or not.                                       | 1.0.0 |
| **`callState`**  | <code><a href="#phonestatetype">PhoneStateType</a></code> | The type of call. 'RINGING' \| 'OUTGOING' \| 'IDLE' \| 'ON_CALL' \| 'ON_HOLD' | 1.0.0 |


### Type Aliases


#### CallStateChangeListener

<code>(status: <a href="#phonestate">PhoneState</a>): void</code>


#### PhoneStateType

<code>'RINGING' | 'OUTGOING' | 'IDLE' | 'ON_CALL' | 'ON_HOLD'</code>

</docgen-api>
